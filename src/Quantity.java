import java.util.Objects;
import java.util.function.DoubleBinaryOperator;

public class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {
        if (unit == null || !Double.isFinite(value))
            throw new IllegalArgumentException("Invalid value or unit");
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    // ---------- ENUM FOR OPERATIONS ----------

    private enum ArithmeticOperation {
        ADD((a, b) -> a + b),
        SUBTRACT((a, b) -> a - b),
        DIVIDE((a, b) -> {
            if (b == 0.0) throw new ArithmeticException("Division by zero");
            return a / b;
        });

        private final DoubleBinaryOperator op;

        ArithmeticOperation(DoubleBinaryOperator op) {
            this.op = op;
        }

        public double compute(double a, double b) {
            return op.applyAsDouble(a, b);
        }
    }

    // ---------- VALIDATION ----------

    private void validate(Quantity<U> other, U target, boolean requireTarget) {
        if (other == null)
            throw new IllegalArgumentException("Other quantity is null");

        if (!Double.isFinite(other.value))
            throw new IllegalArgumentException("Invalid numeric value");

        if (!unit.getClass().equals(other.unit.getClass()))
            throw new IllegalArgumentException("Different measurement categories");

        if (requireTarget && target == null)
            throw new IllegalArgumentException("Target unit is null");
    }

    // ---------- CORE HELPER ----------

    private double performBaseArithmetic(Quantity<U> other, ArithmeticOperation op) {
        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        return op.compute(base1, base2);
    }

    // ---------- CONVERSION ----------

    public Quantity<U> convertTo(U target) {
        if (target == null)
            throw new IllegalArgumentException();

        double base = unit.convertToBaseUnit(value);
        double result = target.convertFromBaseUnit(base);

        return new Quantity<>(round(result), target);
    }

    // ---------- ADD ----------

    public Quantity<U> add(Quantity<U> other) {
        return add(other, this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U target) {
        validate(other, target, true);

        double baseResult = performBaseArithmetic(other, ArithmeticOperation.ADD);
        double result = target.convertFromBaseUnit(baseResult);

        return new Quantity<>(round(result), target);
    }

    // ---------- SUBTRACT ----------

    public Quantity<U> subtract(Quantity<U> other) {
        return subtract(other, this.unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U target) {
        validate(other, target, true);

        double baseResult = performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);
        double result = target.convertFromBaseUnit(baseResult);

        return new Quantity<>(round(result), target);
    }

    // ---------- DIVIDE ----------

    public double divide(Quantity<U> other) {
        validate(other, null, false);

        return performBaseArithmetic(other, ArithmeticOperation.DIVIDE);
    }

    // ---------- EQUALS ----------

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Quantity<?> other)) return false;

        if (!unit.getClass().equals(other.unit.getClass()))
            return false;

        double v1 = unit.convertToBaseUnit(value);
        double v2 = other.unit.convertToBaseUnit(other.value);

        return Math.abs(v1 - v2) < 1e-6;
    }

    @Override
    public int hashCode() {
        return Objects.hash(unit.convertToBaseUnit(value));
    }

    @Override
    public String toString() {
        return value + " " + unit.getUnitName();
    }

    private double round(double v) {
        return Math.round(v * 100.0) / 100.0;
    }
}