import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class LinkedList<T extends Comparable<T>> implements Iterable<T> {
    private Node<T> first, last;
    private int size;

    public LinkedList() {
        first = null;
        last = null;
        size = 0;
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

    public void removeEvery(int n){
        if(n<=0||n>size){
            return;
        }
        if(n==1){
            first=last=null;
        }
        int cont=0;
        Node<T> currentNode=first;
        Node<T> prevNode=null;
        while(currentNode!=null){
            cont++;
            if(cont%n==0){
                prevNode.setNext(currentNode.getNext());
            }else{
                prevNode=currentNode;
                currentNode=currentNode.getNext();
            }
        }
        size=size-size/n;
    }

    public List<LinkedList<T>> extractGroupsOf(int n){
        if(n==0||n>size){
            List<LinkedList<T>> lst=new java.util.LinkedList<LinkedList<T>>();
            return lst;
        }
        int cont=0;
        List<LinkedList<T>> extractedList=new java.util.LinkedList<LinkedList<T>>();
        Node<T> currentNode=first;

        while(currentNode!=null&&cont<=n){
            cont=1;
            LinkedList<T> group=new LinkedList<>();
            while(cont<=n){
                cont++;
                group.add(currentNode.getData());
                currentNode=currentNode.getNext();
            }
            extractedList.add(group);
            cont=1;
        }
        return extractedList;
    }


    // TODO implement me!
    private class LinkedListIterator implements Iterator<T> {
        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public T next() {
            return null;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }
}
