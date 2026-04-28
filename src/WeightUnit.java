public enum WeightUnit {
    KILOGRAM(1.0),
    GRAM(0.001),
    POUND(0.453592);

    private final double toKgFactor;

    WeightUnit(double factor) {
        this.toKgFactor = factor;
    }

    public double convertToBaseUnit(double value) {
        return value * toKgFactor;
    }

    public double convertFromBaseUnit(double kg) {
        return kg / toKgFactor;
    }
}