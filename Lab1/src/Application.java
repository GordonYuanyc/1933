package src;

/**
 *
 */
public class Application{
    

    public static void main(String[] args){

	    int currentYear = 2017;
	    int bornYear = 1997;
	    int days = (currentYear - bornYear)*365;
	    int numerator = 18;
	    int denominator = 9;
	    float Numerator = 18;
	    float Denominator = 9;
	    String firstName = "Hello";
	    String lastName = "World";
	//    String fullName = String firstName + String lastName;

	//    System.out.println("Hello World!");
	//    System.out.println(numerator/denominator);
	//    System.out.println(days);
	//    System.out.println(Numerator/Denominator);
	//    System.out.println(numerator/Denominator);
	//    System.out.println("My first name is - " + firstName);
	//    System.out.println("My last name is - " + lastName);
	//    System.out.println("My full name is - " + firstName + " " + lastName);
	//    System.out.println("Name: " + firstName + " " + lastName + ". The number of days between " + bornYear + " and " + currentYear + " is " + days); 

	    BankAccount myAccount = new BankAccount("Java", "CSCI1933 rules!", 100.50);
//	    myAccount.password = "CSCI1933 rules!";
	    myAccount.deposit("CSCI1933 rules!", 100.50);
	    System.out.println("My account's balance is: " + myAccount.getBalance("CSCI1933 rules!"));
    }

    

}    

