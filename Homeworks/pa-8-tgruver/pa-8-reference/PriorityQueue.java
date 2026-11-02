/**
 * A class to understand the Priority Queue
 * @version: 11 
 */

import java.util.NoSuchElementException;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collection;

public class PriorityQueue<E> implements Cloneable{
    // ArrayList where the nodes of the heap are stored
    private ArrayList<E> list;
    private Comparator<E> comp;
    /**
     * Default Constructor
     */
    public PriorityQueue(){
        list = new ArrayList<>();
        comp = null;
    }
    /**
     * Constructor with one arg
     * @param c the comparator object used to order the nodes of the heap
     * Time complexity: O(1)
     */
    public PriorityQueue(Comparator<E> c){
        list = new ArrayList<>();
        comp = c;
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
        if(comp == null)
            return ((Comparable)value1).compareTo(value2);
        else
            return comp.compare(value1, value2);
    }
    /**
     * Method size
     * @return the number of nodes in the heap
     * Time complexity: O(1)
     */
    public int size(){
        return list.size(); 
    }
    /**
     * Method isEmpty
     * @return true if the heap is empty, false otherwise
     * Time complexity: O(1)
     */
    public boolean isEmpty(){
        return list.isEmpty();
    }
    /**
     * Method to empty the heap
     * Time complexity: O(1)
     */
    public void clear(){
        list.clear(); 
    }
    /**
     * Method toString
     * @return a formatted string containing the values of the nodes of the heap
     * Time complexity: O(n)
     */
    public String toString(){
        return list.toString();
    }
    /**
     * Method to access the value of the root
     * @return the value of the root
     * Time complexity: O(1)
     */
    public E peek(){
        if (list.isEmpty()){
            throw new NoSuchElementException();
        }
        return list.get(0);
    }
    /**
     * Method offer
     * @param value to be added to the heap
     * reconstructs the heap to keep the MinHeap properties
     * Time complexity: O(logn)
     */
    public void offer(E value) {
        list.add(value);
        int currentIndex = list.size()-1; 
        checkHeap(currentIndex, true);
    }
    /**
     * Method pop
     * @return the value of the root, null if the heap is empty
     * reconstructs the heap to keep the MinHeap properties
     * Time complexity: O(logn)
     */
    public E poll() {
        if(list.isEmpty()) 
            return null;
        E removedItem = list.get(0);
        list.set(0, list.get(list.size()-1));
        list.remove(list.size()-1);
        int currentIndex = 0;
        checkHeap(currentIndex, false);
        return removedItem;
    }

    // The Time Complexity is O(N)
    /**
     * checkHeap method
     * @param nodeIndex node where the checking should start
     * @param upDown true if checking from nodeIndex up to the root
     *               false if checking from nodeIndex down to the end of the heap 
     */
    private void checkHeap(int nodeIndex, boolean upDown) {
        if (upDown==true){
            if (nodeIndex==0){
                return;
            }
            E temp = list.get(nodeIndex);
            int parent = (nodeIndex-1)/2;

            if (compare(temp, list.get(parent))<0){
                list.set(nodeIndex, list.get(parent));
                list.set(parent,temp);
                checkHeap(parent,true);
            }
            
        }
        else{
            int left_child= 2*nodeIndex+1;
            int right_child= left_child +1; 

            if (left_child>=list.size()){
                return; 
            }
            int compareChild = left_child;
            if (right_child<list.size() && compare(list.get(right_child), list.get(left_child))<0){
                    compareChild = right_child;
            }
            if(compare(list.get(nodeIndex), list.get(compareChild))<=0){
                return;
            }

            E temp = list.get(nodeIndex);
            
            list.set(nodeIndex, list.get(compareChild));
            list.set(compareChild, temp);
            checkHeap(compareChild, false);     
            }
    
        }


    
    // The Time Complexity is O(N)
    /**
     * Search method
     * @param o the object to lookup
     * @return true of o is found, false otherwise
     */
    public boolean contains(Object o){
        boolean isFound = false; 
        for (int i = 0; i< list.size(); i++){
            if(list.get(i).equals(o)){
                isFound = true;
            }
        }
        return isFound;
    }
    // The Time Complexty is O(1)
    /**
        iterator() method
        @return iterator object pointing to the first element in the list
     */
    public Iterator<E> iterator(){
        return new PriorityQueueIterator();
    }
   /*******************************************************
    * Inner class to implement the interface Iterator<E>  *
    * (ascending order implemented by the iterator)       *
    *******************************************************/
    private class PriorityQueueIterator implements Iterator<E>{
         private int index =0;
        /**
         * Default constructor
         */
        public PriorityQueueIterator(){      
        }
        /**
         *  @return true if current did not reach the end of the list, false otherwise
         */
        public boolean hasNext(){
            if(index<list.size()){
                return true;
            }
            return false;
        }
        /**
         *    @return the value of the current element and moves to the next element
         *    @throws ArrayIndexOutOfBoundsException if no element can be accessed
         */
        public E next(){
            if(hasNext()==false){
                throw new ArrayIndexOutOfBoundsException();
            }
            E value = list.get(index);
            index++;
            return value;
        }

    }
    //The Time Complexity is O(N)
    /**
     * Method to remove a given object
     * @param o the object to find and remove
     * @return true if o was found and removed, false if o was not found
     */
    public boolean remove(Object o){
        boolean isRemoved = false;
        for(int i = 0; i<list.size();i++){
            if(list.get(i).equals(o)){
                if(i == list.size()-1){
                    list.remove(list.size()-1);
                }
                else {
                    list.set(i, list.get(list.size()-1));
                    list.remove(list.size()-1);
                    E swap = list.get(i);
                    int parent =0; 
                    if(i>0){
                        parent = (i-1)/2;
                    }
                    if(compare(swap,list.get(parent))<0){
                        checkHeap(i, true);
                    }
                    else{
                        checkHeap(i, false);
                    }
                }
                isRemoved = true;
                break;
            }
        }
        return isRemoved;
    }

    // The Time Complexity is O(N)
    /**
     * clone this heap
     * @return a deep copy of this heap
     */
    public Object clone(){
        PriorityQueue<E> clone = new PriorityQueue<>(comp);
        for(int i = 0; i<list.size(); i++){
            clone.offer(list.get(i));
        }
        return clone;
    }

    //The Time Complexity is O(Nlogn)
    /**
     * Adds the elements of the collection c to this list
     * @param c the collection to be added to this list
     * @return true if the addition was successful
     * Time complexity: O(n), where n is the size of the collection c
     */
    public boolean addAll(Collection<?> c){
        boolean toBeAdded = false;
        Iterator<?> iter = c.iterator();
        while(iter.hasNext()){
            offer((E) iter.next());
            toBeAdded = true;
        }
         return toBeAdded;
    }
    // The Time Complexity is O(N^2)
    /**
     * removes the elements of this heap that are not in the collection c
     * @param c the collection to be compared to this heap
     * @return true if the intersection was successful
     */
    public boolean retainAll(Collection<?> c){
        boolean toBeRetained = false;
        for(int i = 0; i<list.size(); i++){
          if(!c.contains(list.get(i))){
            remove(list.get(i));
            i= i-1;
            toBeRetained = true;
          }
        }
        return toBeRetained;
    }

    //The Time Complexity is O(N^2)
    /**
     * removes the elements of this heap that are in the collection c
     * @param c the collection to be compared to this heap
     * @return true if the difference was successful
     */
    public boolean removeAll(Collection<?> c){
        boolean toBeRemoved = false;
        Iterator<?> iter = c.iterator();
        while(iter.hasNext()){
            boolean removed = remove(iter.next());
            if (removed){
                toBeRemoved=true;
            }

        }
        return toBeRemoved;
    }
}
