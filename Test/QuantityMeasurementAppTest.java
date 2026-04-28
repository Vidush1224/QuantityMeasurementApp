import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    @Test
    void testFeetToInches() {
        assertEquals(12.0,
                QuantityLength.convert(1, LengthUnit.FEET, LengthUnit.INCHES),
                1e-6);
    }

    @Test
    void testInchesToFeet() {
        assertEquals(2.0,
                QuantityLength.convert(24, LengthUnit.INCHES, LengthUnit.FEET),
                1e-6);
    }

    @Test
    void testYardsToInches() {
        assertEquals(36.0,
                QuantityLength.convert(1, LengthUnit.YARDS, LengthUnit.INCHES),
                1e-6);
    }

    @Test
    void testCentimeterToInches() {
        assertEquals(1.0,
                QuantityLength.convert(2.54, LengthUnit.CENTIMETERS, LengthUnit.INCHES),
                1e-3);
    }

    @Test
    void testEquality() {
        assertEquals(
                new QuantityLength(1, LengthUnit.YARDS),
                new QuantityLength(36, LengthUnit.INCHES));
    }

    @Test
    void testInvalidUnit() {
        assertThrows(IllegalArgumentException.class,
                () -> QuantityLength.convert(1, null, LengthUnit.FEET));
    }

    @Test
    void testInvalidValue() {
        assertThrows(IllegalArgumentException.class,
                () -> QuantityLength.convert(Double.NaN, LengthUnit.FEET, LengthUnit.INCHES));
    }
}