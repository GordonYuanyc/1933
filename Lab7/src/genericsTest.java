public class genericsTest {
    public static void main(String args[]){
        AList<Integer> list1 = new AList();
        AList<String> list2 = new AList();
        Integer[] tempList = new Integer[2];

        tempList[0]=1000;
        tempList[1]=1000;

        for(int i=0;i<30;i++){
            list1.add(i);
        }
        //String a = list1.toArray().toString();
        list1.add(25,100);
        list1.add(1000,100);
        System.out.println(list1.contains(100));
        list1.add(tempList);
        for (int i =0;i<list1.getLength();i++){
            System.out.println("item"+i+"in list1 is: "+list1.get(i));
        }
        System.out.println(list1.remove(1000)+" length"+list1.getLength());
        /*for (int i =0;i<list1.getLength();i++){
            System.out.println("item"+i+"in list1 is: "+list1.get(i));
        }*/

        /*for(int i= 0;i<30;i++){
            list2.add("String");
        }
        list2.add(20,"TEST");
        list2.add(1000,"TEST");
        //String b = list2.toArray().toString();
        for (int i =0;i<list2.getLength();i++){
            System.out.println("item"+i+"in list2 is: "+list2.get(i));
        }
        System.out.println(list2.remove(20)+" length"+list2.getLength());
*/
        list2.add("these");
        list2.add("could");
        list2.add("be");
        list2.add("strings");
        System.out.println(list2.toString());
    }
}
