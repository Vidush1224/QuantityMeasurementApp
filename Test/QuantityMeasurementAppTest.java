public class QuantityMeasurementAppTest {

    @Test
    void testAddFeetTargetFeet() {
        assertEquals(
            new QuantityLength(2, LengthUnit.FEET),
            new QuantityLength(1, LengthUnit.FEET)
                .add(new QuantityLength(12, LengthUnit.INCHES), LengthUnit.FEET)
        );
    }

    @Test
    void testAddFeetTargetInches() {
        assertEquals(
            new QuantityLength(24, LengthUnit.INCHES),
            new QuantityLength(1, LengthUnit.FEET)
                .add(new QuantityLength(12, LengthUnit.INCHES), LengthUnit.INCHES)
        );
    }

    @Test
    void testAddFeetTargetYards() {
        assertEquals(
            new QuantityLength(0.666666, LengthUnit.YARDS),
            new QuantityLength(1, LengthUnit.FEET)
                .add(new QuantityLength(12, LengthUnit.INCHES), LengthUnit.YARDS)
        );
    }

    @Test
    void testAddYardFeetTargetFeet() {
        assertEquals(
            new QuantityLength(6, LengthUnit.FEET),
            new QuantityLength(36, LengthUnit.INCHES)
                .add(new QuantityLength(1, LengthUnit.YARDS), LengthUnit.FEET)
        );
    }

    @Test
    void testAddCmInchTargetCm() {
        assertEquals(
            new QuantityLength(5.08, LengthUnit.CENTIMETERS),
            new QuantityLength(2.54, LengthUnit.CENTIMETERS)
                .add(new QuantityLength(1, LengthUnit.INCHES), LengthUnit.CENTIMETERS)
        );
    }

    @Test
    void testInvalidTarget() {
        assertThrows(IllegalArgumentException.class,
            () -> new QuantityLength(1, LengthUnit.FEET)
                .add(new QuantityLength(1, LengthUnit.FEET), null)
        );
    }
}