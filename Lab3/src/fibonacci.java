public class fibonacci{
    public int fibonacciRecursive(int n) {
        //int i = 0;
        int p = 0;
        int q = 1;
        if (n == 0) {
            return p;
        } else if (n == 1) {
            return q;
        } else {
            p = fibonacciRecursive(n-2) + fibonacciRecursive(n - 1);
            return p;
        }
        //return p;
    }
}