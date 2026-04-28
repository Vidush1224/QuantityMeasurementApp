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
        this.value = value;
        this.unit = unit;
    }

    private double toFeet() {
        return unit.toFeet(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof QuantityLength)) return false;
        QuantityLength other = (QuantityLength) obj;
        return Math.abs(this.toFeet() - other.toFeet()) < 0.0001;
    }
}

public class QuantityMeasurementApp {
    public static void main(String[] args) {

        System.out.println(new QuantityLength(1, LengthUnit.FEET)
                .equals(new QuantityLength(12, LengthUnit.INCHES))); // true

        System.out.println(new QuantityLength(1, LengthUnit.YARDS)
                .equals(new QuantityLength(3, LengthUnit.FEET))); // true

        System.out.println(new QuantityLength(1, LengthUnit.CENTIMETERS)
                .equals(new QuantityLength(0.393701, LengthUnit.INCHES))); // true
    }
}