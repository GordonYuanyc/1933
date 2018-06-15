// Jingchao Fang   fangx260   ID:5302626
//Yancheng Yuan    yuanx322   ID:5299684
import java.util.*;

public class BinaryTree<K extends Comparable<K>, V> {
    private Node<K, V> root;

    public BinaryTree(Node root) {
        this.root = root;
    }

    public Node<K, V> getRoot() {
        return this.root;
    }

    public void add(K key, V value) {
        if (root == null) {
            root = new Node(key, value);
        } else if (root.getKey() == key) {
            root.setValue(value);
        } else if (root.getKey().compareTo(key) > 0) {
            if(root.getLeft()!=null){
                BinaryTree<K, V> temp = new BinaryTree<>(root.getLeft());
                temp.add(key, value);
            }else{
                root.setLeft(new Node(key,value));
            }

        } else {
            if(root.getRight()!=null){
                BinaryTree<K, V> temp = new BinaryTree<>(root.getRight());
                temp.add(key, value);
            }else{
                root.setRight(new Node(key,value));
            }

        }
    }

    public V find(K key) {
        if(root.getKey()==key)  return this.root.getValue();
        else if(root.getKey().compareTo(key)>0&&root.getLeft()!=null){
            BinaryTree<K,V> tempTree=new BinaryTree<>(root.getLeft());
            return tempTree.find(key);
        }else if(root.getKey().compareTo(key)<0&&root.getLeft()!=null){
            BinaryTree<K,V> tempTree=new BinaryTree<>(root.getRight());
            return tempTree.find(key);
        }else   return null;
    }

    public int getNumberOfNodes(){
        int cont=0;
        Node<K,V> currentNode=root;

        if(currentNode==null)   cont=0;
        else{
            cont++;
            cont+=new BinaryTree<K,V>(currentNode.getLeft()).getNumberOfNodes();
            cont+=new BinaryTree<K,V>(currentNode.getRight()).getNumberOfNodes();
        }
        return cont;
    }

    @SuppressWarnings("unchecked")
    public V[] flatten() {
        int numberOfNodes=getNumberOfNodes();
        V[] returnArray=(V[]) new Object[numberOfNodes];
        List<Node<K,V>> treeValue=getAllNodes();
        for(int i=0;i<treeValue.size()-1;i++){
            for(int j=0;j<treeValue.size()-1;j++){
                K temp1=treeValue.get(j).getKey();
                K temp2=treeValue.get(j+1).getKey();
                if(temp1.compareTo(temp2)>0){
                    Node<K,V> temp=treeValue.get(j);
                    treeValue.set(j,treeValue.get(j+1));
                    treeValue.set(j+1,temp);
                }
            }
        }
        for(int i=0;i<returnArray.length;i++){
            returnArray[i]=treeValue.get(i).getValue();
        }

        return returnArray;
    }

    public List<Node<K,V>> getAllNodes(){
        List<Node<K,V>> origin=new LinkedList<>();
        Node<K,V> currentNode=root;
        if(currentNode!=null){
            origin.add(currentNode);
            BinaryTree<K,V> leftSubTree=new BinaryTree<K,V>(currentNode.getLeft());
            origin.addAll(leftSubTree.getAllNodes());
            BinaryTree<K,V> rightSubTree=new BinaryTree<K,V>(currentNode.getRight());
            origin.addAll(rightSubTree.getAllNodes());
        }
        return origin;
    }

    public K maxKey(){
        Node<K,V> currentNode=this.root;
        while(currentNode.getRight()!=null){
            currentNode=currentNode.getRight();
        }
        return currentNode.getKey();
    }

    public K minKey(){
        Node<K,V> currentNode=this.root;
        while(currentNode.getLeft()!=null){
            currentNode=currentNode.getLeft();
        }
        return currentNode.getKey();
    }

    public void remove(K key) {
        if(key.compareTo(minKey())>=0&&key.compareTo(maxKey())<=0){
            Node<K,V> currentNode=this.root;
            while(currentNode.getKey()!=key){
                if(currentNode.getKey().compareTo(key)>0){
                    currentNode=currentNode.getLeft();
                }else {
                    currentNode = currentNode.getRight();
                }
            }//remove currentNode
            if(currentNode==this.root){
                Node<K,V> left=this.root.getLeft();
                Node<K,V> right=this.root.getRight();
                Node<K,V> min=getMinValue(currentNode);
                Node<K,V> parent=getUpperNode(min);
                if(parent!=this.root){
                    if(min.getRight()!=null){
                        if(parent.getLeft()==min)   parent.setLeft(min.getRight());
                        else if(parent.getRight()==min)  parent.setRight(min.getRight());
                    }else{
                        if(parent.getLeft()==min)   parent.setLeft(null);
                        else if(parent.getRight()==min)  parent.setRight(null);
                    }

                    min.setLeft(left);
                    min.setRight(right);
                    this.root=min;
                }else{
                    min.setLeft(left);
                    this.root=min;
                }
            }else if(currentNode.getLeft()==null&&currentNode.getRight()==null){
                Node<K,V> parent=getUpperNode(currentNode);
                if(parent.getLeft().getKey()==key)  parent.setLeft(null);
                else if(parent.getRight().getKey()==key)    parent.setRight(null);
            }else{//node at middle
                if(currentNode.getLeft()==null||currentNode.getRight()==null){
                    if(currentNode.getLeft()==null) getUpperNode(currentNode).setRight(currentNode.getRight());
                    else if(currentNode.getRight()==null)   getUpperNode(currentNode).setLeft(currentNode.getLeft());
                }else{//two child
                    Node<K,V> min=getMinValue(currentNode);
                    Node<K,V> parent=getUpperNode(currentNode);
                    Node<K,V> left=currentNode.getLeft();
                    Node<K,V> right=currentNode.getRight();
                    Node<K,V> minParent=getUpperNode(min);
                    if(minParent!=currentNode){
                        if(min.getRight()!=null){
                            if(minParent.getLeft()==min)    minParent.setLeft(min.getRight());
                            else if(minParent.getRight()==min)   minParent.setRight(min.getRight());
                        }else{
                            if(minParent.getLeft()==min)    minParent.setLeft(null);
                            else if(minParent.getRight()==min)   minParent.setRight(null);
                        }
                        if(parent.getLeft()==currentNode)   parent.setLeft(min);
                        else if(parent.getRight()==currentNode) parent.setRight(min);
                        min.setLeft(left);
                        min.setRight(right);
                    }else{
                        min.setLeft(left);
                    }

                }
            }
        }
    }

    public Node<K,V> getMinValue(Node<K,V> cur){
        Node<K,V> temp=cur.getRight();
        while(temp.getLeft()!=null){
            temp=temp.getLeft();
        }
        return temp;
    }

    public Node<K,V> getUpperNode(Node<K,V> current){
        K key=current.getKey();
        if(root.getLeft().getKey()==key||root.getRight().getKey()==key)  return root;
        else if(root.getKey().compareTo(key)>0){
            BinaryTree<K,V> tempTree=new BinaryTree<>(root.getLeft());
            return tempTree.getUpperNode(current);
        }else if(root.getKey().compareTo(key)<0){
            BinaryTree<K,V> tempTree=new BinaryTree<>(root.getRight());
            return tempTree.getUpperNode(current);
        }else   return null;
    }

    public boolean containsSubtree(BinaryTree<K, V> other) {
        boolean cont=false;
        Queue<Node<K,V>> levelOfThis=new LinkedList<>();
        Queue<Node<K,V>> levelOfOther=new LinkedList<>();
        Node<K,V> currentNode=this.root;
        if(other==null||other.getRoot()==null) return true;
        else{
            while(currentNode.getKey()!=other.getRoot().getKey()&&currentNode!=null){
                if(currentNode.getKey().compareTo(other.getRoot().getKey())>0)  currentNode=currentNode.getLeft();
                else if(currentNode.getKey().compareTo(other.getRoot().getKey())<0) currentNode=currentNode.getRight();
            }
            if(currentNode!=null){//check level by level
                levelOfThis.add(currentNode);
                levelOfOther.add(other.getRoot());
                while(checkLevel(levelOfThis,levelOfOther)){
                    levelOfThis=getNextLevel(levelOfThis);
                    levelOfOther=getNextLevel(levelOfOther);
                }
                if(levelOfOther.isEmpty()){
                    cont=true;
                }
            }else if(currentNode==null){
                cont=false;
            }
            //handle cases: other==null && other.root doesn't exist in BinaryTree
            return cont;
        }

    }

    public Queue<Node<K,V>> getNextLevel(Queue<Node<K,V>> cur){
        Queue<Node<K,V>> temp=new LinkedList<>();
        while(!cur.isEmpty()){
            Node<K,V> current=cur.poll();
            if(current.getLeft()!=null) temp.add(current.getLeft());
            if(current.getRight()!=null)    temp.add(current.getRight());
        }
        return temp;
    }

    public boolean checkLevel(Queue<Node<K,V>> one1,Queue<Node<K,V>> two2){
        Queue<Node<K,V>> one=new LinkedList<>(one1);
        Queue<Node<K,V>> two=new LinkedList<>(two2);
        boolean cont=false;
        if(one.size()==two.size()&&one.size()!=0){
            int counter=0;
            int check=one.size();
            while(!one.isEmpty()&&!two.isEmpty()){
                Node<K,V> first=one.poll();
                Node<K,V> second=two.poll();
                if(nodesComparison(first,second))  counter++;
            }
            if(counter==check)  cont=true;

        }
        return cont;
    }

    public boolean nodesComparison(Node<K,V> one, Node<K,V> two){
        if(one.getKey()==two.getKey()&&one.getValue()==two.getValue())  return true;
        else    return false;
    }
}
