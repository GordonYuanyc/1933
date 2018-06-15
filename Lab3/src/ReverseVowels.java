import java.util.ArrayList;
import java.util.Arrays;

public class ReverseVowels {
    public static String reverseVowel(String s) {
        char[] array = s.toCharArray();
        ArrayList<Character> arrayVowel = new ArrayList();
        ArrayList<Character> arrayVowelReversed = new ArrayList();

        for (int p = 0; p < array.length; p++) {
            if (array[p] == 97) {
                arrayVowel.add(array[p]);
            } else if (array[p] == 101) {
                arrayVowel.add(array[p]);
            } else if (array[p] == 105) {
                arrayVowel.add(array[p]);
            } else if (array[p] == 111) {
                arrayVowel.add(array[p]);
            } else if (array[p] == 117) {
                arrayVowel.add(array[p]);
            }
        }
        for (int i = arrayVowel.size() - 1; i > -0.1; i--) {
            arrayVowelReversed.add(arrayVowel.get(i));
        }
        int cont = 0;
        for (int p = 0; p < array.length; p++) {
            if (array[p] == 97) {
                array[p] = arrayVowelReversed.get(cont);
                cont++;
            } else if (array[p] == 101) {
                array[p] = arrayVowelReversed.get(cont);
                cont++;
            } else if (array[p] == 105) {
                array[p] = arrayVowelReversed.get(cont);
                cont++;
            } else if (array[p] == 111) {
                array[p] = arrayVowelReversed.get(cont);
                cont++;
            } else if (array[p] == 117) {
                array[p] = arrayVowelReversed.get(cont);
                cont++;
            }
        }
        String reversed = "";
        for (int i = 0; i < array.length; i++){
            reversed += array[i];
        }
        return reversed;
    }
}
