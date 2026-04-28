import java.util.Objects;

public class QuantityWeight {
    private final double value;
    private final WeightUnit unit;

    public QuantityWeight(double value, WeightUnit unit) {
        if (unit == null || !Double.isFinite(value))
            throw new IllegalArgumentException();
        this.value = value;
        this.unit = unit;
    }

    private double toKg() {
        return unit.convertToBaseUnit(value);
    }

    public QuantityWeight convertTo(WeightUnit target) {
        if (target == null) throw new IllegalArgumentException();
        double kg = toKg();
        return new QuantityWeight(target.convertFromBaseUnit(kg), target);
    }

    public QuantityWeight add(QuantityWeight other) {
        return add(other, this.unit);
    }

    public QuantityWeight add(QuantityWeight other, WeightUnit target) {
        if (other == null || target == null)
            throw new IllegalArgumentException();

        double sumKg = this.toKg() + other.toKg();
        return new QuantityWeight(target.convertFromBaseUnit(sumKg), target);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof QuantityWeight)) return false;
        QuantityWeight other = (QuantityWeight) obj;
        return Math.abs(this.toKg() - other.toKg()) < 1e-6;
    }

    @Override
    public int hashCode() {
        return Objects.hash(toKg());
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }
}