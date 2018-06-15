public class iterativeReverse{
    public static void iterative(int num){
        while (num % 10 != 0 || num > 5){
            int p = num% 10;
            System.out.print(p);
            num = (num-p)/10;
        }
    }
}