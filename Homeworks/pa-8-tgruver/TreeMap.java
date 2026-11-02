import java.util.Comparator;
import java.util.Collection;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * Class to implement a binary search tree data structure using linked nodes
 */
public class TreeMap<K,V>{
    // data members
    private TreeNode root;
    private int size;
    private Comparator<K> comparator;
    // Inner class for the tree nodes
    private class TreeNode{
        MapEntry<K,V> value;
        TreeNode left, right;

        TreeNode(MapEntry<K,V> val){
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
    private int compare(K key1, K key2) {
        if (comparator == null) {
            return ((Comparable<K>) key1).compareTo(key2);
        } else {
            return comparator.compare(key1, key2);
        }
    }

    /**
     * Default constructor
     * Create an empty binary search tree
     * Time complexity: O(1)
     */
    public TreeMap(){
        root = null;
        size = 0;
        comparator = null;
    }

    /**
     * Constructor with a comparator
     * Create an empty binary search tree
     * Time complexity: O(1)
     */
    public TreeMap(Comparator<K> c){
        root = null;
        size = 0;
        comparator = c;
    }

    /**
     * Getter of the size of the tree
     * @return the number of nodes in the tree
     * Time complexity: O(1)
     */
    public int size(){
        return size;
    }
    /**
     * @return true if the tree is empty, false otherwise
     * Time complexity: O(1)
     */
    public boolean isEmpty(){
        return (size == 0);
    }
    /**
     * clears the tree
     * Time complexity: O(1)
     */
    public void clear(){
        root = null;
        size = 0;
    }
    /**
     * Search method
     * @param element the value being looked up
     * @return true if element is found in the tree, false otherwise
     * Time complexity: O(log n) if data inserted randomly, 
     *                  O(n) is data is sorted before insertion
     */
    public boolean contains(K key, V value){
        if (root == null){
            return false;
        }
        TreeNode current = root;
        while(current != null){
            int c = compare(key, current.value.getKey());
            if(c == 0)
                return true;
            else if(c < 0)
                current = current.left;
            else
                current = current.right;
        }
        return false;   
    }
    
    /**
     * Ading a new element to the tree
     * @param element the value to add in the tree
     * @return true if element was added succesfully, false if element already exists in the tree
     * Time complexity: O(log n) if data inserted randomly, 
     *                  O(n) is data is sorted before insertion
     */
    public boolean add(MapEntry<K,V> element){
        TreeNode newNode = new TreeNode(element);
        if (root == null){
            root = newNode;
        }
        else{
            TreeNode current = root, parent = null;
            while(current != null){
                parent = current;
                int c = compare(element.getKey(), current.value.getKey());
                if(c == 0)
                    return false;
                else if(c < 0)
                    current = current.left;
                else
                    current = current.right;
            }
            if(compare(element.getKey(), parent.value.getKey()) < 0)
                parent.left = newNode;
            else
                parent.right = newNode;
        }
        size++;
        return true;
    }
    /**
     * Removing an element from the tree
     * @param element the value to be removed from the tree
     * @return true if element was found and removed, false if element was not found
     * Time complexity: O(log n) if data inserted randomly, 
     *                  O(n) is data is sorted before insertion
     */
    public boolean remove(K key){
        if (root == null){
            return false;
        }
        // First, find the node with value element
        TreeNode node = root, parent = null;
        while(node != null){
            int c = compare(key, node.value.getKey());
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
            System.out.print(node.value.getKey() + "=" + node.value.getValue()+ " ");

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
            System.out.print(node.value.getKey() + "=" + node.value.getValue() + " ");
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
            System.out.print(node.value.getKey() + "=" + node.value.getValue() + " ");
        }
    }


/**
 * Adds an entry. If the key is already in the tree, its value is replaced.
 * Returns the old value if the key existed, or null if it was new.
 */
    public V put(K key, V value){
        
        if(root == null){
            root = new TreeNode(new MapEntry<>(key, value));
            size++;
            return null; // no new value!
        }

        TreeNode current = root;
        boolean isFound = false;
        while(isFound == false){

            //we want to step through the tree
            int c = compare(key, current.value.getKey());

            //case 1: value found!
            if(c==0) { // then key is in tree! trigger swap. 
                V oldValue = current.value.getValue();
                current.value.setValue(value);
                return oldValue;
            }

            //case 2: slide to the left side of BST
            else if(c < 0){
                //idea: we just keep sliding until either c !< 0 OR we hit a boundary
                if(current.left == null){
                    //if boundary is hit, then we know mapEntry doesn't exist --> add
                    current.left = new TreeNode(new MapEntry<>(key, value));
                    size++;
                    return null;
                }
                current = current.left;
            }

            //case 3: slide to the right side of BST
            else if(c > 0){
                //idea: we just keep sliding until either c !< 0 OR we hit a boundary
                if(current.right == null){
                    //if boundary is hit, then we know mapEntry doesn't exist --> add
                    current.right = new TreeNode(new MapEntry<>(key, value));
                    size++;
                    return null;
                }
                current = current.right;
            }
        }
        return null;
    }


    /** Returns the value for this key, or null if the key is not in the tree. */
    public V get(K key) {
        TreeNode node = findNode(key);
        if (node == null) {
            return null;
        }
        return node.value.getValue();
    }

    /** Returns true if the key is in the tree. */
    public boolean containsKey(K key) {
        return findNode(key) != null;
    }

    /**
     * Looks for the node with this key. Returns the node, or null if not found.
     * Helper for every method that needs tree traversal!
     */
    private TreeNode findNode(K key) {
    /**
     * Other functions will use this... So I should consider against using an iterator due to poor performance (i lose the log(n) property of BST)
     */

    TreeNode current = root;
    while (current != null) {
        int c = compare(key, current.value.getKey());
        if (c == 0) {
            return current;
        } else if (c < 0) {
            current = current.left;
        } else {
            current = current.right;
        }
    }
    //then doesnt have it!
    return null;        
    }

    public MapEntry<K, V> first() {
        if (root == null) {
            return null; 
        }
        TreeNode current = root;
        while (current.left != null) {// BST structure --> Smallest ALWAYS at left
            current = current.left;
        }
        return current.value;
    }

    public MapEntry<K, V> last() {
        if (root == null) {
            return null; 
        }
        TreeNode current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.value;
    }

//RETURN TO THIS METHOD 
    /** Returns an iterator that visits the entries from smallest key to largest. */
    public Iterator<MapEntry<K, V>> iterator() {
        ArrayList<MapEntry<K, V>> entries = new ArrayList<>();   // where we collect the entries
        Stack<TreeNode> pending = new Stack<>();                 // nodes still to visit
        TreeNode current = root;

        while (current != null || !pending.isEmpty()) {
            // Go left as far as possible, remembering each node on the way.
            while (current != null) {
                pending.push(current);
                current = current.left;
            }
            // Nothing further left: take the next smallest node and record it.
            current = pending.pop();
            entries.add(current.value);
            // Then move to its right side and repeat.
            current = current.right;
        }

        return entries.iterator(); 

    }


    /** Returns the entry with the largest key that is <= the given key, or null if none. */
    public MapEntry<K, V> floor(K key) {
        TreeNode current = root;
        MapEntry<K,V> bestKey = null;

        while (current != null) {
            int c = compare(key, current.value.getKey());
            if ( c == 0 ) {
                return current.value; //best case
            } else if (c > 0) { // then curr key is greater than target key, we want to move to the left because by moving to the left, we are inching closer to target key value
                bestKey = current.value; //best canditate is closest element to target, to the left
                current = current.right;
        
            } else if (c<0){
                current = current.left;
            }
        }
        return bestKey;

    }
    /** Returns the entry with the smallest key that is >= the given key, or null if none. */
    public MapEntry<K, V> ceiling(K key) {
        TreeNode current = root;
        MapEntry<K,V> bestKey = null;

        while (current != null) {
            int c = compare(key, current.value.getKey());
            if ( c == 0 ) {
                return current.value; //best case
            } else if (c < 0) { // then curr key is greater than target key, we want to move to the left because by moving to the left, we are inching closer to target key value
                bestKey = current.value; //best canditate is closest element to target, to the left
                current = current.left;
        
            } else if (c>0){
                current = current.right;
            }
        }
        return bestKey;
    }

        /** Puts every entry of c into this map. Returns true if the map changed. */
    public boolean addAll(Collection<MapEntry<K, V>> c) {
        K key = null;
        V value = null;
        boolean isChanged = false;
        for(MapEntry<K,V> entry : c){
            key = entry.getKey();
            value = entry.getValue();
            put(key,value);
            isChanged = true;
        }    
        
        if(isChanged == true)    
            return true;

        return false;
    }

    /** Removes every entry in c (matched by key). Returns true if the map changed. */
    public boolean removeAll(Collection<MapEntry<K, V>> c) {
        boolean didChange = false;
        for (MapEntry<K, V> entry : c) {
            if (remove(entry.getKey())) {
                didChange = true;
            }
        }
        return didChange;
    }

    /** Keeps only the entries whose key appears in c. Returns true if the map changed. */
    public boolean retainAll(Collection<MapEntry<K, V>> c) {
    // Have to store keys to remove while walking through the tree, because if I remove when walking funky stuff can happen
        int oldSize = size;
        TreeMap<K, V> kept = new TreeMap<>(comparator);   

        for (MapEntry<K, V> entry : c) {
            K key = entry.getKey();
            if (containsKey(key)) { 
                kept.put(key, get(key)); //values can be different, so I need to grab the key from the orginal tree!
            }
        }

        root = kept.root; 
        size = kept.size;

        if( size != oldSize)
            return true;
        return false; //if not same, then return false cuz size is changed
    }

    /** Returns a TreeSet holding every key in this map. */
    public TreeSet<K> keys() {
        TreeSet<K> newSet = new TreeSet<>(comparator); 
        Iterator<MapEntry<K, V>> it = iterator();
        while (it.hasNext()) {
            newSet.add(it.next().getKey());
        }
        return newSet;
    }

    public Collection<V> values() {
        ArrayList<V> newSet = new ArrayList<>(); 
        Iterator<MapEntry<K, V>> it = iterator();
        
        while (it.hasNext()) {
            newSet.add(it.next().getValue());
        }
        return newSet;
    }

    /** Returns a copy of this deep map */
    public Object clone() {
        TreeMap<K, V> copy = new TreeMap<>(comparator);
        Iterator<MapEntry<K, V>> it = iterator();
        while (it.hasNext()) {
            MapEntry<K, V> entry = it.next();
            copy.put(entry.getKey(), entry.getValue());   // put() makes a new entry
        }
        return copy;
    }
}