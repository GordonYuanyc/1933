/*
 * Modified from Frank M. Carrano's 
 * Data Structures and Abstractions with Java (3rd Edition)
 */
import java.security.AlgorithmConstraints;
import java.util.Arrays;



public class AList<T>{

    private T[] list = (T[])new Object[25];
    private int numberOfEntries;

    public AList(){
        list = (T[])new Object[25];
        numberOfEntries = 0;
    }

    public AList(int initialCapacity){
        numberOfEntries = 0;
        T[] tempList = (T[])new Object[initialCapacity];
        list = tempList;
    }

    public void add(T newEntry){
        ensureCapacity();
        list[numberOfEntries] = newEntry;
        numberOfEntries++;
    }

    public T get(int item){
        return list[item];
    }

    public int getLength(){
        return numberOfEntries;
    }

    public boolean isEmpty(){
        return numberOfEntries == 0;
    }

    public T[] toArray(){
        T[] result = (T[])new Object[numberOfEntries];
        for(int index = 0; index < numberOfEntries; index++){
          result[index] = list[index];
        }

        return result;
    }

    public boolean add(int newPosition,T element){
        /*if (newPosition == numberOfEntries){
            add(element);
            return true;
        }else if(newPosition>-0.01&&newPosition<numberOfEntries){
            T[] tempList = (T[])new Object[getLength()+1];
            for (int i=0;i<newPosition+0.01;i++){
                tempList[i] = get(i);
            }
            tempList[newPosition]=element;
            for (int i = newPosition+1;i<numberOfEntries+1;i++){
                tempList[i]=get(i-1);
            }
            ensureCapacity();
            list = tempList;
            return true;
        }else{
            return false;
        }*/
        ensureCapacity();
        if(newPosition<0 || newPosition>getLength())return false;
        numberOfEntries++;
        for(int i=numberOfEntries;i >= newPosition;i--) list[i + 1] = list[i];
        list[newPosition]=element;
        return true;
    }

    public T remove(int givenPosition){
        if(givenPosition<=getLength()){
            AList<T> justOne = new AList();
            justOne.add(get(givenPosition));
            numberOfEntries--;
            for(int i=givenPosition;i<=numberOfEntries;i++) list[i]=list[i+1];
            return justOne.get(0);
        }else{
            return null;
        }
    }

    public boolean contains(T element){
        boolean cont=false;
        for(int i=0;i<getLength();i++){
            if(list[i].equals(element)){
                cont = true;
                break;
            }
        }
        return cont;
    }

    public void add(T[] newList){
        for(int i=0;i<newList.length;i++){
            add(newList[i]);
        }
    }

    public String toString(){
        String a = "[";
        String temp = (String) list[0];
        a+=temp;
        for (int i=1;i<numberOfEntries;i++){
            if(list[i]!=null){
                String temp1 = (String) list[i];
                a=a+", "+temp1;
            }
        }
        a+="]";
        return a;
    }

    private void ensureCapacity(){
        if (numberOfEntries == list.length){
            list = Arrays.copyOf(list, 2 * list.length);
        }
    }
}
