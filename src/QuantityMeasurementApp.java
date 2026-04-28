class QuantityLength {
    private final double value;
    private final LengthUnit unit;

    public QuantityLength(double value, LengthUnit unit) {
        if (unit == null || !Double.isFinite(value))
            throw new IllegalArgumentException();
        this.value = value;
        this.unit = unit;
    }

    private double toFeet() {
        return unit.convertToBaseUnit(value);
    }

    public QuantityLength convertTo(LengthUnit target) {
        if (target == null) throw new IllegalArgumentException();
        double feet = toFeet();
        return new QuantityLength(target.convertFromBaseUnit(feet), target);
    }

    public QuantityLength add(QuantityLength other, LengthUnit target) {
        if (other == null || target == null)
            throw new IllegalArgumentException();

        double sumFeet = this.toFeet() + other.toFeet();
        return new QuantityLength(target.convertFromBaseUnit(sumFeet), target);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof QuantityLength)) return false;
        QuantityLength other = (QuantityLength) obj;
        return Math.abs(this.toFeet() - other.toFeet()) < 1e-6;
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }
}

public class QuantityMeasurementApp {
    public static void main(String[] args) {

        System.out.println(
            new QuantityLength(1, LengthUnit.FEET).convertTo(LengthUnit.INCHES)
        ); // 12 INCHES

        System.out.println(
            new QuantityLength(1, LengthUnit.FEET)
                .add(new QuantityLength(12, LengthUnit.INCHES), LengthUnit.FEET)
        ); // 2 FEET

        System.out.println(
            new QuantityLength(36, LengthUnit.INCHES)
                .equals(new QuantityLength(1, LengthUnit.YARDS))
        ); // true
    }
}