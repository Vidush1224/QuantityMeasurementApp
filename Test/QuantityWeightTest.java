public class QuantityWeightTest {

    @Test
    void testKgToGramEquality() {
        assertEquals(
            new QuantityWeight(1, WeightUnit.KILOGRAM),
            new QuantityWeight(1000, WeightUnit.GRAM)
        );
    }

    @Test
    void testKgToPoundEquality() {
        assertEquals(
            new QuantityWeight(1, WeightUnit.KILOGRAM),
            new QuantityWeight(2.20462, WeightUnit.POUND)
        );
    }

    @Test
    void testConversion() {
        assertEquals(
            new QuantityWeight(1000, WeightUnit.GRAM),
            new QuantityWeight(1, WeightUnit.KILOGRAM)
                .convertTo(WeightUnit.GRAM)
        );
    }

    @Test
    void testAdditionSameUnit() {
        assertEquals(
            new QuantityWeight(3, WeightUnit.KILOGRAM),
            new QuantityWeight(1, WeightUnit.KILOGRAM)
                .add(new QuantityWeight(2, WeightUnit.KILOGRAM))
        );
    }

    @Test
    void testAdditionCrossUnit() {
        assertEquals(
            new QuantityWeight(2, WeightUnit.KILOGRAM),
            new QuantityWeight(1, WeightUnit.KILOGRAM)
                .add(new QuantityWeight(1000, WeightUnit.GRAM))
        );
    }

    @Test
    void testAdditionWithTarget() {
        assertEquals(
            new QuantityWeight(2000, WeightUnit.GRAM),
            new QuantityWeight(1, WeightUnit.KILOGRAM)
                .add(new QuantityWeight(1000, WeightUnit.GRAM), WeightUnit.GRAM)
        );
    }

    @Test
    void testInvalid() {
        assertThrows(IllegalArgumentException.class,
            () -> new QuantityWeight(1, null));
    }
}