/*
 * Modified from Frank M. Carrano's
 * Data Structures and Abstractions with Java (3rd Edition)
 */
 import java.util.Arrays;
 import java.util.Scanner;
 import java.io.File;

public class AList<T extends Comparable>{

    private T[] list;
    private int numberOfEntries;

    public AList(){
        list = (T[])new Comparable[25];
        numberOfEntries = 0;
    }

    public AList(int initialCapacity){
        numberOfEntries = 0;
        T[] tempList = (T[]) new Comparable[initialCapacity];
        list = tempList;
    }

    public void add(T newEntry){
        ensureCapacity();
        list[numberOfEntries] = newEntry;
        numberOfEntries++;
    }

    public void add(T[] addList) {
      if(addList.length == 0)
        return;
      for(int i = 0; i < addList.length; i++) {
        add(addList[i]);
      }
    }

    public boolean add(int newPosition, T newEntry) {
      if(newPosition > numberOfEntries || newPosition < 0)
        return false;
      ensureCapacity();
      for(int i = numberOfEntries - 1; i > newPosition; i--) {
        list[i] = list[i - 1];
      }
      list[newPosition] = newEntry;
      numberOfEntries++;
      return true;
    }

    public T remove(int givenPosition) {
      if(givenPosition >= numberOfEntries || givenPosition < 0)
        return null;
      T toReturn = list[givenPosition];
      for(int i = givenPosition; i < numberOfEntries - 1; i++) {
        list[i] = list[i + 1];
      }
      list[numberOfEntries] = null;
      numberOfEntries--;
      return toReturn;
    }

    public T get(int item){
        if(item >= numberOfEntries || item < 0)
          return null;
        return list[item];
    }

    public int getLength(){
        return numberOfEntries;
    }

    public boolean isEmpty(){
        return numberOfEntries == 0;
    }

    public T[] toArray(){
        T[] result = (T[])new Comparable[numberOfEntries];
        for(int index = 0; index < numberOfEntries; index++){
          result[index] = list[index];
        }

        return result;
    }

    private void ensureCapacity(){
        if (numberOfEntries == list.length){
            list = Arrays.copyOf(list, 2 * list.length);
        }
    }

    public boolean contains(T element) {
      for(int i = 0; i < numberOfEntries; i++) {
        if(list[i].equals(element)) {
          return true;
        }
      }
      return false;
    }

    public String toString() {
      String toReturn = "[";
      for(int i = 0; i < numberOfEntries - 1; i++) {
        toReturn += list[i].toString() + ", ";
      }
      toReturn += list[numberOfEntries - 1].toString() + "]";

      return toReturn;
    }

    public static AList<String> fileToAList(File input) {
      Scanner s;
      try {
        s = new Scanner(input);
      } catch(Exception e) {
        System.out.println("There was an error opening the file");
        return null;
      }
      //Your code here
      return null;

    }

    public boolean binarySearch(T element){
        int low=0;
        int high=numberOfEntries;

        while(low<=high){
            int middle=low+(high-low)/2;
            if(list[middle].equals(element)){
                return true;
            }else if(list[middle].compareTo(element)>0){
                high=middle-1;
            }else{
                low=middle+1;
            }
        }
        return false;
    }

    public void intersect(AList<T> other){
        for(int i=0;i<numberOfEntries;i++){
            if(!other.exist(get(i))){
                remove(i);
            }
        }
    }

    private boolean exist(T element){
        boolean cont=false;
        for(int i=0;i<numberOfEntries;i++){
            if(get(i).equals(element)){
                cont=true;
            }
        }
        return cont;
    }
}
