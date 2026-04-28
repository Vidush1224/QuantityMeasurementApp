public class QuantityMeasurementAppTest {

    @Test
    void testConvertFeetToInches() {
        assertEquals(
            new QuantityLength(12, LengthUnit.INCHES),
            new QuantityLength(1, LengthUnit.FEET).convertTo(LengthUnit.INCHES)
        );
    }

    @Test
    void testAddWithTargetFeet() {
        assertEquals(
            new QuantityLength(2, LengthUnit.FEET),
            new QuantityLength(1, LengthUnit.FEET)
                .add(new QuantityLength(12, LengthUnit.INCHES), LengthUnit.FEET)
        );
    }

    @Test
    void testAddWithTargetYards() {
        assertEquals(
            new QuantityLength(0.666666, LengthUnit.YARDS),
            new QuantityLength(1, LengthUnit.FEET)
                .add(new QuantityLength(12, LengthUnit.INCHES), LengthUnit.YARDS)
        );
    }

    @Test
    void testEquality() {
        assertEquals(
            new QuantityLength(36, LengthUnit.INCHES),
            new QuantityLength(1, LengthUnit.YARDS)
        );
    }

    @Test
    void testInvalidUnit() {
        assertThrows(IllegalArgumentException.class,
            () -> new QuantityLength(1, null));
    }
}