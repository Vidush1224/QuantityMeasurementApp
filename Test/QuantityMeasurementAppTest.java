public class QuantityMeasurementAppTest {

    @Test
    void testBasicEquality() {
        assertEquals(
                new QuantityLength(1, LengthUnit.FEET),
                new QuantityLength(12, LengthUnit.INCHES));
    }

    private void assertEquals(QuantityLength quantityLength, QuantityLength quantityLength2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'assertEquals'");
    }

    @Test
    void testYardConversion() {
        assertEquals(
                new QuantityLength(1, LengthUnit.YARDS),
                new QuantityLength(36, LengthUnit.INCHES));
    }

    @Test
    void testCentimeterConversion() {
        assertEquals(
                new QuantityLength(1, LengthUnit.CENTIMETERS),
                new QuantityLength(0.393701, LengthUnit.INCHES));
    }

    @Test
    void testInequality() {
        assertNotEquals(
                new QuantityLength(1, LengthUnit.FEET),
                new QuantityLength(2, LengthUnit.FEET));
    }

    private void assertNotEquals(QuantityLength quantityLength, QuantityLength quantityLength2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'assertNotEquals'");
    }
}