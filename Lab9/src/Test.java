import java.util.Arrays;
import java.util.Iterator;

public class Test {
    public static void main(String args[]){
        LinkedList<Integer> LL=new LinkedList<>();
        LinkedList<Integer> LList=new LinkedList<>();
        LList.add(4);
        LList.add(2);
        LList.add(3);
        LList.add(7);
        LList.add(3);
        for(int i=0;i<30;i++){
            LL.add(i+1);
        }
        Iterator<Integer> temp=LL.iterator();
        while(temp.hasNext()){
            System.out.println(temp.next());
        }
        System.out.println(Arrays.toString(LinkedList.xify(LList)));
        System.out.println(LinkedList.countingSort(LList).toString());

    }

}
