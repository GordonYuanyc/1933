import java.util.Scanner;

public class Fib {
    public static void main(String args[]) {
        Scanner whatNumber = new Scanner(System.in);
        String num = null;
        System.out.print("The number: ");
        num = whatNumber.nextLine();

        int n;
        n = Integer.valueOf(num);

        fibonacci myclass = new fibonacci();
        int answer = myclass.fibonacciRecursive(n);

        System.out.println("fibonacciRecursive(" + n + ")   " + answer);
    }
}
