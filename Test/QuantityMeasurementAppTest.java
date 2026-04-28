import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    @Test
    void testFeetPlusFeet() {
        assertEquals(
            new QuantityLength(3, LengthUnit.FEET),
            new QuantityLength(1, LengthUnit.FEET)
                .add(new QuantityLength(2, LengthUnit.FEET))
        );
    }

    @Test
    void testFeetPlusInches() {
        assertEquals(
            new QuantityLength(2, LengthUnit.FEET),
            new QuantityLength(1, LengthUnit.FEET)
                .add(new QuantityLength(12, LengthUnit.INCHES))
        );
    }

    @Test
    void testInchesPlusFeet() {
        assertEquals(
            new QuantityLength(24, LengthUnit.INCHES),
            new QuantityLength(12, LengthUnit.INCHES)
                .add(new QuantityLength(1, LengthUnit.FEET))
        );
    }

    @Test
    void testYardsPlusFeet() {
        assertEquals(
            new QuantityLength(2, LengthUnit.YARDS),
            new QuantityLength(1, LengthUnit.YARDS)
                .add(new QuantityLength(3, LengthUnit.FEET))
        );
    }

    @Test
    void testCmPlusInch() {
        assertEquals(
            new QuantityLength(5.08, LengthUnit.CENTIMETERS),
            new QuantityLength(2.54, LengthUnit.CENTIMETERS)
                .add(new QuantityLength(1, LengthUnit.INCHES))
        );
    }

    @Test
    void testConvert() {
        assertEquals(12.0,
            QuantityLength.convert(1, LengthUnit.FEET, LengthUnit.INCHES),
            1e-6);
    }

    @Test
    void testInvalid() {
        assertThrows(IllegalArgumentException.class,
            () -> new QuantityLength(1, null));
    }
}