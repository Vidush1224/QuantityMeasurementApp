public enum VolumeUnit implements IMeasurable {

    LITRE(1.0),
    MILLILITRE(0.001),
    GALLON(3.78541);

    private final double factor;

    VolumeUnit(double factor) {
        this.factor = factor;
    }

    @Override
    public double getConversionFactor() {
        return factor;
    }

    @Override
    public double convertToBaseUnit(double value) {
        return value * factor; // to litres
    }

    @Override
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / factor; // from litres
    }

    @Override
    public String getUnitName() {
        return name();
    }
}