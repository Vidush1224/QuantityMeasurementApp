import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-2;

    // ---------- LENGTH TESTS ----------

    @Test
    void lengthFeetEqualsInches() {
        Quantity<LengthUnit> f = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> i = new Quantity<>(12.0, LengthUnit.INCHES);
        assertTrue(f.equals(i));
    }

    @Test
    void lengthYardsEqualsFeet() {
        Quantity<LengthUnit> y = new Quantity<>(1.0, LengthUnit.YARDS);
        Quantity<LengthUnit> f = new Quantity<>(3.0, LengthUnit.FEET);
        assertTrue(y.equals(f));
    }

    @Test
    void convertFeetToInches() {
        Quantity<LengthUnit> f = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> res = f.convertTo(LengthUnit.INCHES);
        assertEquals(12.0, res.getValue(), EPSILON);
    }

    @Test
    void addFeetAndInches() {
        Quantity<LengthUnit> f = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> i = new Quantity<>(12.0, LengthUnit.INCHES);
        Quantity<LengthUnit> res = f.add(i, LengthUnit.FEET);
        assertEquals(2.0, res.getValue(), EPSILON);
    }

    // ---------- WEIGHT TESTS ----------

    @Test
    void weightKgEqualsGrams() {
        Quantity<WeightUnit> kg = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> g = new Quantity<>(1000.0, WeightUnit.GRAM);
        assertTrue(kg.equals(g));
    }

    @Test
    void weightPoundEqualsGrams() {
        Quantity<WeightUnit> lb = new Quantity<>(1.0, WeightUnit.POUND);
        Quantity<WeightUnit> g = new Quantity<>(453.592, WeightUnit.GRAM);
        assertTrue(lb.equals(g));
    }

    @Test
    void convertKgToGrams() {
        Quantity<WeightUnit> kg = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> res = kg.convertTo(WeightUnit.GRAM);
        assertEquals(1000.0, res.getValue(), EPSILON);
    }

    @Test
    void addKgAndGrams() {
        Quantity<WeightUnit> kg = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> g = new Quantity<>(1000.0, WeightUnit.GRAM);
        Quantity<WeightUnit> res = kg.add(g, WeightUnit.KILOGRAM);
        assertEquals(2.0, res.getValue(), EPSILON);
    }

    // ---------- GENERIC BEHAVIOR ----------

    @Test
    void preventCrossTypeEquality() {
        Quantity<LengthUnit> l = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<WeightUnit> w = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        assertFalse(l.equals(w));
    }

    @Test
    void constructorRejectsNullUnit() {
        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(1.0, null));
    }

    @Test
    void constructorRejectsInvalidValue() {
        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(Double.NaN, LengthUnit.FEET));
    }

    @Test
    void additionIsCommutative() {
        Quantity<LengthUnit> a = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(12.0, LengthUnit.INCHES);

        assertEquals(
                a.add(b, LengthUnit.FEET).getValue(),
                b.add(a, LengthUnit.FEET).getValue(),
                EPSILON
        );
    }

    @Test
    void zeroHandling() {
        Quantity<WeightUnit> w = new Quantity<>(5.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> zero = new Quantity<>(0.0, WeightUnit.GRAM);

        Quantity<WeightUnit> res = w.add(zero);
        assertEquals(5.0, res.getValue(), EPSILON);
    }
}