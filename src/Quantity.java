import java.util.Objects;

public class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {
        if (unit == null || !Double.isFinite(value))
            throw new IllegalArgumentException();
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    // ---------- CONVERSION ----------

    public Quantity<U> convertTo(U target) {
        if (target == null)
            throw new IllegalArgumentException();

        double base = unit.convertToBaseUnit(value);
        double result = target.convertFromBaseUnit(base);

        return new Quantity<>(round(result), target);
    }

    // ---------- ADDITION ----------

    public Quantity<U> add(Quantity<U> other) {
        return add(other, this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U target) {
        if (other == null || target == null)
            throw new IllegalArgumentException();

        if (!unit.getClass().equals(other.unit.getClass()))
            throw new IllegalArgumentException("Different categories");

        double sumBase =
                unit.convertToBaseUnit(value) +
                        other.unit.convertToBaseUnit(other.value);

        double result = target.convertFromBaseUnit(sumBase);
        return new Quantity<>(round(result), target);
    }

    // ---------- SUBTRACTION ----------

    public Quantity<U> subtract(Quantity<U> other) {
        return subtract(other, this.unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U target) {
        if (other == null || target == null)
            throw new IllegalArgumentException();

        if (!unit.getClass().equals(other.unit.getClass()))
            throw new IllegalArgumentException("Different categories");

        double baseResult =
                unit.convertToBaseUnit(value) -
                        other.unit.convertToBaseUnit(other.value);

        double result = target.convertFromBaseUnit(baseResult);
        return new Quantity<>(round(result), target);
    }

    // ---------- DIVISION ----------

    public double divide(Quantity<U> other) {
        if (other == null)
            throw new IllegalArgumentException();

        if (!unit.getClass().equals(other.unit.getClass()))
            throw new IllegalArgumentException("Different categories");

        double divisor = other.unit.convertToBaseUnit(other.value);

        if (divisor == 0.0)
            throw new ArithmeticException("Division by zero");

        double baseThis = unit.convertToBaseUnit(value);

        return baseThis / divisor;
    }

    // ---------- EQUALITY ----------

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