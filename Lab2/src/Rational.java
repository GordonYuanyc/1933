public class Rational{

    private int numerator = 1;
    private int denominator = 1;

    public Rational(int a, int b){
        numerator = a;
        denominator = b;
    }

    public void setNumerator(int value){
        numerator = value;
    }
    public void setDenominator(int value){
        denominator = value;
    }
    public int getNumerator(){
        return numerator;
    }
    public int getDenominator(){
        return denominator;
    }

    public void addRational(Rational r){
        numerator = numerator*r.getDenominator() + r.getNumerator()*denominator;
        denominator = r.getDenominator()*denominator;
    }
    public void subRational(Rational r){
        numerator = numerator*r.getDenominator() - r.getNumerator()*denominator;
        denominator = r.getDenominator()*denominator;
    }
    public void mulRational(Rational r){
        numerator = numerator*r.getNumerator();
        denominator = denominator*r.getDenominator();
    }
    public void divRational(Rational r){
        numerator = numerator*r.getDenominator();
        denominator = denominator*r.getNumerator();
    }


}