/*
 * Modified from Frank M. Carrano's
 * Data Structures and Abstractions with Java (3rd Edition)
 */
 import java.io.FileNotFoundException;
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
      for(int i = numberOfEntries; i > newPosition; i--) {
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
      list[numberOfEntries - 1] = null;
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
      if(isEmpty())
        return "[]";
      String toReturn = "[";
      for(int i = 0; i < numberOfEntries - 1; i++) {
        toReturn += list[i].toString() + ", ";
      }
      toReturn += list[numberOfEntries - 1].toString() + "]";

      return toReturn;
    }

    public static AList<String> fileToAList(File input) {
        Scanner s;
        AList<String> fileString = new AList();
        AList<String> tempAList = new AList();
        try {
            s = new Scanner(input);
        } catch(Exception e) {
            System.out.println("There was an error opening the file");
            return null;
        }
        //Your code here
        while (s.hasNextLine()) {
            String line = s.nextLine();
            System.out.println(line);
            fileString.add(line);
        }
        s.close();
        for(int i=0;i<fileString.getLength();i++){
            //System.out.println(fileString.get(i));
            tempAList.add("0");
        }
        for(int i=0;i<fileString.getLength();i++){
            String tempString = fileString.get(i);
            String[] tempArray = tempString.split(",");
            tempAList.remove(Integer.parseInt(tempArray[0]));
            tempAList.add(Integer.parseInt(tempArray[0]),tempArray[1]);
        }
        fileString = tempAList;
      return fileString;

    }

    public AList<T> slice(int start, int stop){
        /*if(start<0){
            start = 0;
        }
        if(stop>numberOfEntries-1){
            stop = numberOfEntries-1;
        }
        AList<T> newList = new AList();
        for(int counter = start;counter<stop;counter++){
            newList.add(list[counter]);
        }
        return newList;*/
        return slice(start,stop,1);
    }

    public AList<T> slice(int start,int stop,int step){
        if(start<0){
            start = 0;
        }
        if(stop>numberOfEntries-1){
            stop = numberOfEntries-1;
        }
        AList<T> newList = new AList();
        for(int counter = start;counter<stop;counter+=step){
            newList.add(list[counter]);
        }
        return newList;
    }

    public void sort(boolean ascending){
        /*AList<T> newList1 = new AList();
        AList<T> newList2 = new AList();
        int half = (numberOfEntries-numberOfEntries%2)/2;
        for(int i=0;i<numberOfEntries;i++){
            if ((list[half].compareTo(list[i])<0)==ascending){
                newList1.add(list[i]);
            }else{
                newList2.add(list[i]);
            }
        }
        for(int i=0;i<newList1.getLength();i++){
            list[i]=newList1.toArray()[i];
        }
        for(int i=0;i<newList2.getLength();i++){
            list[i+newList1.getLength()]=newList2.toArray()[i];
        }
        if(newList1.getLength()>1 || newList2.getLength()>1){
            newList1.sort(ascending);
            newList2.sort(ascending);
        }*/
        for(int p=1;p<numberOfEntries;p++){
            for(int i=1;i<numberOfEntries;i++){
                if ((list[i].compareTo(list[i-1])>0)==ascending){
                    T newEntry =list[i-1];
                    list[i-1]=list[i];
                    list[i]=newEntry;
                }
            }
        }

    }

    /*public static AList<String> fileToAList(File input) {
        File file = new File("input.txt");
        AList<String> fileString = new AList();
        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                fileString.add(line);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
        }
        return fileString;
    }*/
}
