public class QuantityMeasurementApp {

    public static <U extends IMeasurable> boolean demonstrateEquality(
            Quantity<U> q1, Quantity<U> q2) {
        return q1.equals(q2);
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateConversion(
            Quantity<U> q, U target) {
        return q.convertTo(target);
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateAddition(
            Quantity<U> q1, Quantity<U> q2) {
        return q1.add(q2);
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateAddition(
            Quantity<U> q1, Quantity<U> q2, U target) {
        return q1.add(q2, target);
    }

    public static void main(String[] args) {

        Quantity<LengthUnit> l1 = new Quantity<>(10, LengthUnit.FEET);
        Quantity<LengthUnit> l2 = new Quantity<>(120, LengthUnit.INCHES);

        System.out.println(demonstrateEquality(l1, l2));
        System.out.println(demonstrateConversion(l1, LengthUnit.INCHES));
        System.out.println(demonstrateAddition(l1, l2));

        Quantity<WeightUnit> w1 = new Quantity<>(1, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2 = new Quantity<>(1000, WeightUnit.GRAM);

        System.out.println(demonstrateEquality(w1, w2));
        System.out.println(demonstrateConversion(w1, WeightUnit.GRAM));
        System.out.println(demonstrateAddition(w1, w2));
    }
}