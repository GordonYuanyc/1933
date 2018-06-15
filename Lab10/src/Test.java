import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Test {
    public static void main(String args[]){
        /*AList<Integer> AL1=new AList();
        AList<Integer> AL2=new AList();
        AList<Integer> AL3=new AList();
        AL1.add(1);
        AL1.add(3);
        AL1.add(9);
        AL1.add(12);
        AL1.add(15);
        AL1.add(18);
        AL1.add(20);
        AL2.add(1);
        AL2.add(2);
        AL2.add(3);
        AL2.add(4);
        AL3.add(2);
        AL3.add(4);
        AL3.add(6);
        /*for(int i=0;i<21;i++){
            while(AL1.binarySearch(i)){
                System.out.println(i);
                break;
            }
        }
        if(AL1.binarySearch(20)){
            System.out.println("It exists.");
        }else{
            System.out.println("It doesn't.");
        }
        AL2.intersect(AL3);
        System.out.println(AL2.toString());*/
        LinkedList<Integer> LL=new LinkedList<>();
        for(int i=0;i<30;i++){
            LL.add(i+1);
        }
        //Iterator<Integer> temp=LL.iterator();
        LL.removeEvery(2);
        System.out.println(LL.toString());
        List<LinkedList<Integer>> listOfList=LL.extractGroupsOf(3);
        System.out.println(listOfList);
    }

}
