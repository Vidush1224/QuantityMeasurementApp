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
}

class QuantityLength {
    private final double value;
    private final LengthUnit unit;

    public QuantityLength(double value, LengthUnit unit) {
        if (unit == null) throw new IllegalArgumentException("Unit cannot be null");
        if (!Double.isFinite(value)) throw new IllegalArgumentException("Invalid value");
        this.value = value;
        this.unit = unit;
    }

    private double toFeet() {
        return unit.toFeet(value);
    }

    public static double convert(double value, LengthUnit from, LengthUnit to) {
        if (from == null || to == null) throw new IllegalArgumentException("Unit cannot be null");
        if (!Double.isFinite(value)) throw new IllegalArgumentException("Invalid value");

        double inFeet = from.toFeet(value);
        return inFeet / to.toFeet(1.0);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof QuantityLength)) return false;
        QuantityLength other = (QuantityLength) obj;
        return Math.abs(this.toFeet() - other.toFeet()) < 1e-6;
    }
}

public class QuantityMeasurementApp {
    public static void main(String[] args) {

        System.out.println(QuantityLength.convert(1, LengthUnit.FEET, LengthUnit.INCHES)); // 12
        System.out.println(QuantityLength.convert(3, LengthUnit.YARDS, LengthUnit.FEET)); // 9
        System.out.println(QuantityLength.convert(36, LengthUnit.INCHES, LengthUnit.YARDS)); // 1

        System.out.println(new QuantityLength(1, LengthUnit.YARDS)
                .equals(new QuantityLength(3, LengthUnit.FEET))); // true
    }
}