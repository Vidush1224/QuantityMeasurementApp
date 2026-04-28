enum LengthUnit {
    FEET(1.0),
    INCHES(1.0 / 12.0),
    YARDS(3.0),
    CENTIMETERS(0.0328084);

    private final double toFeetFactor;

    LengthUnit(double factor) {
        this.toFeetFactor = factor;
    }

    public double toFeet(double value) {
        return value * toFeetFactor;
    }

    public double fromFeet(double feet) {
        return feet / toFeetFactor;
    }
}

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
        return unit.toFeet(value);
    }

    public QuantityLength add(QuantityLength other) {
        if (other == null) throw new IllegalArgumentException();

        double sumFeet = this.toFeet() + other.toFeet();
        double result = unit.fromFeet(sumFeet);

        return new QuantityLength(result, this.unit);
    }

    public static double convert(double value, LengthUnit from, LengthUnit to) {
        if (from == null || to == null || !Double.isFinite(value))
            throw new IllegalArgumentException();

        double feet = from.toFeet(value);
        return to.fromFeet(feet);
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

        System.out.println(new QuantityLength(1, LengthUnit.FEET)
                .add(new QuantityLength(12, LengthUnit.INCHES))); // 2 FEET

        System.out.println(new QuantityLength(1, LengthUnit.YARDS)
                .add(new QuantityLength(3, LengthUnit.FEET))); // 2 YARDS

        System.out.println(QuantityLength.convert(1, LengthUnit.FEET, LengthUnit.INCHES)); // 12
    }
}