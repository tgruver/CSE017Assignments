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
     * Time complexity: O(1)
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
        bubbleUp(currentIndex);
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

        E removedItem = list.get(0); //saving the root to return at the end!
        list.set(0, list.get(list.size()-1));// last entry into the root
        list.remove(list.size()-1); //then remove the duplicate last entry
        int currentIndex = 0;
        bubbleDown(currentIndex); //repair structure
        return removedItem;
    }

    /**
     * bubble up method
     * checks if the current node at nodeIndex is less than its parent
     * if yes, swap them and recursively moves up to the parent
     * @param nodeIndex node where the checking should start
     *  
     */
    private void bubbleUp(int nodeIndex) {
        int parentIndex = (nodeIndex - 1) / 2;

        if(compare(list.get(nodeIndex), list.get(parentIndex)) < 0){
            E temp = list.get(nodeIndex);
            list.set(nodeIndex, list.get(parentIndex));
            list.set(parentIndex, temp);
            bubbleUp(parentIndex);
        }
    }

    /**
     * bubble down method
    * @param nodeIndex node where the checking should start
     *  
     */
    private void bubbleDown(int nodeIndex) {
        int leftIndex = 2 * nodeIndex + 1, rightIndex = 2 * nodeIndex + 2;

        //left child node always gets filled first, so if that doesn't exist then we know that no children exist for current node
        if(leftIndex >= list.size()){
            return;
        }

        int smallerIndex = leftIndex; 
        if (rightIndex < list.size() && compare(list.get(rightIndex), list.get(leftIndex)) < 0) {
            smallerIndex = rightIndex;
        } //to make sure we swap the right value

        if (compare(list.get(smallerIndex), list.get(nodeIndex)) < 0) {
            E temp = list.get(nodeIndex);
            list.set(nodeIndex, list.get(smallerIndex));
            list.set(smallerIndex, temp);
            bubbleDown(smallerIndex); 
        }

    }
    /**
     * Search method
     * @param o the object to lookup
     * @return true of o is found, false otherwise
     */
    public boolean contains(Object o){
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).equals(o)){
                return true;
            }
        }
        return false;
    }
    /**
        iterator() method
        @return iterator object pointing to the root of the heap
     */
    public Iterator<E> iterator(){
        return new PriorityQueueIterator();
    }
   /***************************************************************
    * Inner class to implement the interface Iterator<E>          *
    * (natural or custom order implemented by the iterator)       *
    ***************************************************************/
    private class PriorityQueueIterator implements Iterator<E>{
        private ArrayList<E> sorted = new ArrayList<>();
        private int index = 0;

        /**
         * Default constructor
         */
        public PriorityQueueIterator(){ 
            PriorityQueue<E> copy = (PriorityQueue<E>) PriorityQueue.this.clone(); //we use a clone because we don't want to poll the actual queue - by polling the clone, we are left with a sorted arraylist that we can iterate through normally
            while (!copy.isEmpty()) {
                sorted.add(copy.poll());
            }
    
        }
        /**
         *  @return true if current did not reach the end of the list, false otherwise
         */
        public boolean hasNext(){
            return index < sorted.size();
        }
        /**
         *    @return the value of the current element and moves to the next element
         *    @throws ArrayIndexOutOfBoundsException if no element can be accessed
         */
        public E next(){
            if (!hasNext()) {
                throw new ArrayIndexOutOfBoundsException();
            }
            E value = sorted.get(index);
            index++;   
            return value;
        }

    }
    /**
     * Method to remove a given object
     * @param o the object to find and remove
     * @return true if o was found and removed, false if o was not found
     */
    public boolean remove(Object o){
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(o)) { 
                int lastIndex = list.size() - 1;
                list.set(i, list.get(lastIndex));
                list.remove(lastIndex); 
               if (i < list.size()) { //if not at tend of queue,                     
                    bubbleUp(i);
                    bubbleDown(i);                       
                }
                return true; 
            }
        }

        return false;
    }
    /**
     * clone this heap
     * @return a deep copy of this heap
     */
    public Object clone(){
        PriorityQueue<E> copy = new PriorityQueue<>(comp);
        copy.list = new ArrayList<>(list);

        return copy;
    }
    /**
     * Adds the elements of the collection c to this list
     * @param c the collection to be added to this list
     * @return true if the addition was successful
     * Time complexity: O(n), where n is the size of the collection c
     */
    public boolean addAll(Collection<?> c){
        boolean isChanged = false;
        for (Object element : c) {
            offer((E) element);
             isChanged = true;
        }
        return isChanged;

    }
    /**
     * removes the elements of this heap that are not in the collection c
     * @param c the collection to be compared to this heap
     * @return true if the intersection was successful
     */
    public boolean retainAll(Collection<?> c){
        boolean isChanged = false;
        Iterator<E> it = iterator();   
        while (it.hasNext()) {
            E element = it.next();
            if (!c.contains(element)) { 
                remove(element);
                isChanged = true;
            }
        }
        return isChanged;
    }

    /**
     * removes the elements of this heap that are in the collection c
     * @param c the collection to be compared to this heap
     * @return true if the difference was successful
     */
    public boolean removeAll(Collection<?> c){
        boolean isChanged = false;
        for (Object element : c) {
            if(remove((E) element))
             isChanged = true;
        }
        return isChanged;
    }
}