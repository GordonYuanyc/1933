public class Test{
    public static void main(String args[]){
	Complex3 c = new Complex3(3.0,3.0);
	Complex3 d = new Complex3(4.0,4.0);
	c.addComplex(d);
	d.subtractComplex(c);

	double expectedRealc = 7.0;
	double actualRealc = c.getRealPart();
	boolean result = expectedRealc - actualRealc < 0.001;
	System.out.println("TestAddreal: " + result);
	
	double expectedImaginaryc = 7.0;
	double actualImaginaryc = c.getImaginaryPart();
	boolean result2 = expectedImaginaryc - actualImaginaryc < 0.001;
	System.out.println("TestAddImaginary: " + result2);

	double expectedReald = -3.0;
	double actualReald = d.getRealPart();
	boolean result1 = expectedReald - actualReald < 0.001;
	System.out.println("TestSubtractreal: " + result1);

	double expectedImaginaryd = -3.0;
	double actualImaginaryd = d.getImaginaryPart();
	boolean result3 = expectedImaginaryd - actualImaginaryd < 0.001;
	System.out.println("TestSubtractImaginary: " + result3);

	c.setRealPart(10.0);
	double extectedSetC = 10.0;
	boolean result4 = extectedSetC - c.getRealPart() < 0.001;
	System.out.println("TestSetreal: " + result4);
	


	
	
    }


}
