import sun.rmi.server.InactiveGroupException;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T extends Comparable<T>> implements Iterable<T> {
    private Node<T> first, last;
    private int size;

    public LinkedList() {
        first = null;
        last = null;
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean add(T element) {
        // for the sake of lab, let's _not_ allow null data
        if(element == null) return false;

        Node<T> newNode = new Node<>(element);

        if(size == 0)
            first = newNode;
        else
            last.setNext(newNode);

        last = newNode;
        size++;

        return true;
    }

    public String toString() {
        String ret = "[";
        Node<T> ptr = first;
        for(int i = 0; i < size; i++) {
            ret += ptr.getData().toString() + ", ";
            ptr = ptr.getNext();
        }

        return size == 0 ? ret + "]" : ret.substring(0, ret.length() - 2) + "]";
    }

    public static int[] xify(LinkedList<Integer> x){
        LinkedList<Integer> big=new LinkedList<>();
        Iterator<Integer> temp =x.iterator();
        int subsize=0;
        while(temp.hasNext()){
            Integer current=temp.next();
            for(int i=0;i<current;i++){
                big.add(current);
            }
            subsize+=current;
        }
        Node<Integer> tempNode=big.first;
        int[] finalInt=new int[subsize];
        for(int i=0;i<subsize;i++){
            finalInt[i]=tempNode.getData();
            tempNode=tempNode.getNext();
        }
        return finalInt;
    }

    public static LinkedList<Integer> countingSort(LinkedList<Integer> lst){
        Iterator<Integer> lstIterator=lst.iterator();
        int biggest=0;
        while(lstIterator.hasNext()){
            int temp=lstIterator.next();
            if(temp>biggest){
                biggest=temp;
            }
        }
        int[] counts=new int[biggest+1];
        Iterator<Integer> newLstIterator=lst.iterator();
        while(newLstIterator.hasNext()){
            int temp=newLstIterator.next();
            counts[temp]=counts[temp]+1;
            /*for(int i=0;i<temp;i++){

            }*/
        }
        LinkedList<Integer> ret=new LinkedList<>();
        for(int i=1;i<counts.length;i++){
            if(counts[i]!=0){
                //ret.add(counts[i]);
                for(int j=0;j<counts[i];j++){
                    ret.add(i);
                }
            }


        }
        return ret;
    }

    public void reverse(){
        Node<T> current=first;
        while(current.getNext()!=null){
            current.getNext().setNext(current);//some problem here
        }
    }

    // TODO implement me!
    private class LinkedListIterator implements Iterator<T> {
        Node<T> current = first;
        public boolean hasNext() {
            boolean cont=true;
            if(current==null){
                cont=false;
            }
            return cont;
        }

        @Override
        public T next() {
            T data=current.getData();
            current=current.getNext();
            return data;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }
}
