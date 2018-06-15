import java.io.File;

public class AlistTest {
    public static void main(String args[]){
        AList<Integer> list1 = new AList();

        for(int i=1;i<31;i++){
            list1.add(i);
        }

        list1.sort(false);
        String stringSort = list1.toString();
        String string1 = list1.slice(1,20).toString();
        String string2 = list1.slice(-200,345).toString();
        String string3 = list1.slice(1,10,4).toString();
        String string4 = list1.slice(-2134,42654,4).toString();

        /*System.out.println(string1);
        System.out.println(string2);
        System.out.println(string3);
        System.out.println(string4);*/
        System.out.println(stringSort);
        //File file = new File("C:/Users/Gordon/Desktop/1933/Lab8/src/input.txt");
        String String1 = "C:/Users/Gordon/Desktop/1933/Lab8/src/input.txt";
        File file = new File(String1);
        //AList.fileToAList(file);
        System.out.println(AList.fileToAList(file).toString());
    }

}
