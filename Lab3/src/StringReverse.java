import java.util.Scanner;

public class StringReverse {
    public static void main(String args[]){
        Scanner whatString = new Scanner(System.in);
        String str = null;
        System.out.println("The string is: ");
        str = whatString.nextLine();

        String result = ReverseVowels.reverseVowel(str);

        System.out.println(result);
    }
}
