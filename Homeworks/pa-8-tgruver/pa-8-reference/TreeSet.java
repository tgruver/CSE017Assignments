/**
 * A class to understand the TreeSet
 * @author: Sean Abarca 
 * @version: 11 
 */

import java.util.Comparator;
import java.util.Collection;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Class to implement a binary search tree data structure using linked nodes
 */

/**
 * For some reason half of my test cases for this sections didn't show up, so
 * I have no clue if I implemented the methods correctly. 
 */
public class TreeSet<E> implements Cloneable{
    // data members
    private TreeNode root;
    private int size;
    private Comparator<E> comparator;
    // Inner class for the tree nodes
    private class TreeNode{
        E value;
        TreeNode left, right;
        TreeNode(E val){
            value = val;
            left = right = null;
        }
    }
    /**
     * private method to compare two E values using natural ordering or the comparator object
     * @param value1 the first value
     * @param value2 the second value
     * @return 0   if value1 and value2 are equal
     *         > 0 if value1 is after value2
     *         < 0 if value1 is before value2
     */
    private int compare(E value1, E value2){
        if(comparator == null){
            return ((Comparable) value1).compareTo(value2);
        }
        else{
            return comparator.compare(value1, value2);
        }
    }
    /**
     * Default constructor
     * Create an empty binary search tree
     */
    public TreeSet(){
        root = null;
        size = 0;
        comparator = null;
    }
    /**
     * Constructor with a comparator
     * Create an empty binary search tree
     */
    public TreeSet(Comparator<E> c){
        root = null;
        size = 0;
        comparator = c;
    }
    /**
     * Getter of the size of the tree
     * @return the number of nodes in the tree
     */
    public int size(){
        return size;
    }
    /**
     * @return true if the tree is empty, false otherwise
     */
    public boolean isEmpty(){
        return (size == 0);
    }
    /**
     * clears the tree
     */
    public void clear(){
        root = null;
        size = 0;
    }

    // For both contain methods, the Time complexity is O(logN)
    /**
     * Search method
     * @param element the value being looked up
     * @return true if element is found in the tree, false otherwise
     */
    public boolean contains(E element){
        return contains(element, root);
    }

    /**
     * Helper method
     * @param element the value being looked up
     * @param node the node being compared to 
     * @return true if element is found in the tree, false otherwise
     */
    public boolean contains(E element, TreeNode node){
        if (node == null){
            return false;
        }

        int c = compare(element, node.value);
        if(c == 0){
            return true;
        }
        else if(c < 0){
            return contains(element, node.left);
        }
        else{
             return contains(element, node.right);
        } 
    }

    // For both add methods, the Time Complexity is O(logN)

    /**
     * Adding a new element to the tree
     * @param element the value to add in the tree
     * @return true if element was added succesfully, false if element already exists in the tree
     */
    public boolean add(E element){
        int oldSize = size;
        root = add(element, root);
        return size>oldSize;
    }

    /**
     * Helper Method
     * Adding a new element to the tree
     * @param element the value to add in the tree
     * @param node the node being compared to to be added 
     * @return true if element was added succesfully, false if element already exists in the tree
     */
    public TreeNode add(E element, TreeNode node){
        TreeNode newNode = new TreeNode(element);
        if (node == null){
            node = newNode;
            size++;
            return node;
        }
        else{
            int c = compare(element, node.value);
            if(c == 0){
                return node;
            }
            else if(c < 0){
                node.left = add(element, node.left);
               return node;
            }
            else{
                node.right = add(element, node.right);
                return node;
            }
        }
    }

    //The time complexity is O(logN).
    /**
     * Removing an element from the tree
     * @param element the value to be removed from the tree
     * @return true if element was found and removed, false if element was not found
     */
    public boolean remove(E element){
        if (root == null){
            return false;
        }
        // First, find the node with value element
        TreeNode node = root, parent = null;
        while(node != null){
            int c = compare(element, node.value);
            if(c == 0){
                break;
            }
            parent = node;
            if(c < 0)
                node = node.left;
            else
                node = node.right;
        }
        if (node == null){
            return false; // value not found
        }
        // case 1: Leaf node
        if(node.left == null && node.right == null){
            if(parent == null){ // the only node in the tree
                root = null; size = 0;
            }
            else{
                if(parent.left == node)
                    parent.left = null;
                else
                    parent.right = null;
            }
        }
        // case 2: node has a right child only
        else if(node.left == null){
            if(parent == null)
                root = node.right;
            else if (parent.left == node)
                parent.left = node.right;
            else   
                parent.right = node.right;
        }
        // case 2: node has a left child only
        else if(node.right == null){
            if(parent == null)
                root = node.left;
            else if(parent.left == node)
                parent.left = node.left;
            else
                parent.right = node.left;
        }
        // case 3: node has two children
        else{
            TreeNode rightMostCurrent = node.left;
            TreeNode rightMostParent = node;
            while(rightMostCurrent.right != null){
                rightMostParent = rightMostCurrent;
                rightMostCurrent = rightMostCurrent.right;
            }
            node.value = rightMostCurrent.value;
            if(rightMostParent.left == rightMostCurrent)
                rightMostParent.left = rightMostCurrent.left;
            else
                rightMostParent.right = rightMostCurrent.left;
        }
        size--;
        return true;
    }
   
    /**
     * Preorder Traversal method
     */
    public void preorder(){
        System.out.print("[");
        preorder(root);
        System.out.println("]");
    }
    /**
     * Preorder Traversal recursive helper method
     * @param node the node where the traversal starts
     */
    public void preorder(TreeNode node){
        if(node != null){
            System.out.print(node.value + " ");
            preorder(node.left);
            preorder(node.right);
        }
    }
    /**
     * Inorder Traversal method
     */
    public void inorder(){
        System.out.print("[");
        inorder(root);
        System.out.println("]");
    }
    /**
     * Inorder Traversal recursive helper method
     * @param node the node where the traversal starts
     */
    public void inorder(TreeNode node){
        if(node != null){
            inorder(node.left);
            System.out.print(node.value + " ");
            inorder(node.right);
        }
    }
    /**
     * Postorder Traversal method
     */
    public void postorder(){
        System.out.print("[");
        postorder(root);
        System.out.println("]");
    }
    /**
     * Postorder Traversal recursive helper method
     * @param node the node where the traversal starts
     */
    public void postorder(TreeNode node){
        if(node != null){
            postorder(node.left);
            postorder(node.right);
            System.out.print(node.value + " ");
        }
    }  
    // The Time Complexity is O(logN)
    /**
     * Adds the elements of the collection c to this tree
     * @param c the collection to be added to this tree
     * @return true if the addition was successful
     */
    public boolean addAll(Collection<? extends E> c){
        boolean toBeAdded = false;
        Iterator<?> iter = c.iterator();
        while(iter.hasNext()){
             boolean added = add((E) iter.next());
                if (added){
                    toBeAdded = true;
                }
             }
         return toBeAdded;
    }

    // The Time Complexity is O(logN)
    /**
     * removes the elements of this tree that are not in the collection c
     * @param c the collection to be compared to this tree
     * @return true if the intersection was successful
     */
    public boolean retainAll(Collection<?> c){
        boolean toBeRetained = false;
        Iterator<?> iter = iterator();
        while(iter.hasNext()){
          E element = (E) iter.next();  
          if(!c.contains(element)){
            boolean removedValue = remove(element);
            if(removedValue){
                toBeRetained = true;
            }
          }
        }
        return toBeRetained;
    }

    // The Time Complexity is O(logN)
    /**
     * removes the elements of this tree that are in the collection c
     * @param c the collection to be compared to this tree
     * @return true if the difference was successful
     */
    public boolean removeAll(Collection<?> c){
        boolean toBeRemoved = false;
        Iterator<?> iter = c.iterator();
        while(iter.hasNext()){
            boolean removed = remove((E)iter.next());
            if (removed){
                toBeRemoved=true;
            }
        }
        return toBeRemoved;
    }

    //For both first methods, the time complexity is O(1) 
    /**
     * first method
     * @return the lowest value in the tree
     */
    public E first(){
        return first(root);
    }

    /**
     * helper method
     * @param node the node that is being compared to
     * @return the lowest value in the tree
     */
    public E first(TreeNode node){
        if (node.left==null){
            return node.value;
        }
        return first(node.left);
    }

    //For both last methods, the time complexity is O(1) 
    /**
     * last method
     * @return the highest value in the tree
     */
    public E last(){
        return last(root);
    }

    /**
     * helper method
     * @param node the node that is being compared ot 
     * @return the highest value in the tree
     */
    public E last(TreeNode node){
        if(node.right==null){
            return node.value;
        }
        return last(node.right);
    }

    // For both ceiling method, the time complexity is O(logN)
    /**
     * ceiling method
     * @return the least element in this tree greater than or equal to the given value, or
     *         null if there is no such element
     */
    public E ceiling(E value){
        return ceiling(value, root);
    }

    /**
     * helper method
     * @param node the node being compared to 
     * @return the least element in this tree greater than or equal to the given value, or
     *         null if there is no such element
     */
    public E ceiling(E value, TreeNode node){
        if(node==null){
            return null;
        }
        int c = compare(value, node.value);
        if (c==0){
            return node.value;
        }
        else if(c > 0){
            return ceiling(value, node.right);
        }
        else{
            E answer = ceiling(value, node.left);
            if (answer!=null){
                return answer;
            }
            else{
                return node.value;
            }
           
        }
    }

    // For both floor methods, the time complexity is O(logn)
    /**
     * floor method
     * @return the greatest element in this tree less than or equal to the given value, or 
     *         null if there is no such element
     */
    public E floor(E value){
        return floor(value, root);
    }
    
    /**
     * Helper method
     * @param node the node being compared to 
     * @return the greatest element in this tree less than or equal to the given value, or 
     *         null if there is no such element
     */
    public E floor(E value, TreeNode node){
         if(node==null){
            return null;
        }
        int c = compare(value, node.value);
        if (c==0){
            return node.value;
        }
        else if(c < 0){
            return floor(value, node.left);
        }
        else{
            E answer = floor(value, node.right);
            if (answer!=null){
                return answer;
            }
            else{
                return node.value;
            }
           
        }
    }
    // The Time Complexity is O(1)
    /**
     *   iterator() method
     *   @return iterator object pointing to the first element in the inorder traversal
     */
    public Iterator<E> iterator(){
        return new TreeSetIterator();
    }
    /*******************************************************
     * Inner class to implement the interface Iterator<E>  *
     * (inorder traversal implemented by the iterator)     *
     *******************************************************/
    private class TreeSetIterator implements Iterator<E>{
        /**
         * Default constructor
         */
        public TreeSetIterator(){
        }
        /**
         *    @return true if there is a next element to access in the tree, false otherwise
         */
        public boolean hasNext(){
            // if(root==null){
            //     return false;
            // }
            // return true;
            return false;
        }
        /**
         * @return the value of the current element and moves to the next element
         * @throws ArrayIndexOutOfBoundsException if there is no current element
         */
        public E next(){
            // if(hasNext()==false){
            //     throw new ArrayIndexOutOfBoundsException();
            // }
            // inorder(root);
            // return root.value;
            return null;
        }
    }
    // The Time Complexity is O(N log N)
    /**
     * clone this tree
     * @return a deep copy of this tree
     */
    public Object clone(){
        TreeSet<E> clone = new TreeSet<E>(comparator);
        Iterator<E> iter = iterator();
        while(iter.hasNext()){
            clone.add(iter.next());
        }
        return clone;
    }
}