public class recursiveReverse{
    public static void recursive(int num){
        if (num % 10 == 0 && num < 5){
            return;
        }else{
            int p = num % 10;
            System.out.print(p);
            int i = (num - p)/10;
            recursive(i);
        }
    }
}