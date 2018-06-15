public class TestRational{
    public static void main(String args[]){
        Rational p = new Rational(2,3);
        Rational q = new Rational(3,4);

        p.addRational(q);
        Rational a = new Rational(p.getNumerator(),p.getDenominator());
        p.setNumerator(2);
        p.setDenominator(3);

        q.subRational(p);
        Rational b = new Rational(q.getNumerator(),q.getDenominator());
        q.setNumerator(3);
        q.setDenominator(4);

        p.mulRational(q);
        Rational c = new Rational(p.getNumerator(),p.getDenominator());
        p.setNumerator(2);
        p.setDenominator(3);

        p.divRational(q);
        Rational d = new Rational(p.getNumerator(),p.getDenominator());
        q.setNumerator(3);
        q.setDenominator(4);

        int expectAddRationalan = 17;
        int actualAddRationalan = a.getNumerator();
        boolean result1 = expectAddRationalan - actualAddRationalan < 0.0001;
        System.out.println("TestAddRationalNominator: " + result1);

        int expectAddRationalad = 12;
        int actualAddRationalad = a.getDenominator();
        boolean result2 = expectAddRationalad - actualAddRationalad < 0.0001;
        System.out.println("TestAddRationalDenominator: " + result2);

        int expectSubRationalbn = 1;
        int actualSubRationalbn = b.getNumerator();
        boolean result3 = expectSubRationalbn - actualSubRationalbn < 0.0001;
        System.out.println("TestSubRationalNominator: " + result3);

        int expectSubRationalbd = 12;
        int actualSubRationalbd = b.getDenominator();
        boolean result4 = expectSubRationalbd - actualSubRationalbd < 0.0001;
        System.out.println("TestSubRationalDenominator: " + result4);

        int expectMulRationalcn = 6;
        int actualMulRationalcn = c.getNumerator();
        boolean result5 = expectMulRationalcn - actualMulRationalcn < 0.0001;
        System.out.println("TestMulRationalNominator: " + result5);

        int expectMulRationalcd = 12;
        int actualMulRationalcd = c.getDenominator();
        boolean result6 = expectMulRationalcd - actualMulRationalcd < 0.0001;
        System.out.println("TestMulRationalDenominator: " + result6);

        int expectDivRationaldn = 8;
        int actualDivRationaldn = d.getNumerator();
        boolean result7 = expectDivRationaldn - actualDivRationaldn < 0.0001;
        System.out.println("TestDivRationalNominator: " + result7);

        int expectDivRationaldd = 9;
        int actualDivRationaldd = d.getDenominator();
        boolean result8 = expectDivRationaldd - actualDivRationaldd < 0.0001;
        System.out.println("TestDivRationalDenominator: " + result8);

        p.setNumerator(100);
        int expect = 100;
        boolean result0 = expect - p.getNumerator() < 0.001;
        System.out.println("TestSetNumerator: " + result0);
    }
}