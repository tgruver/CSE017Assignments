/**
 * Generic class to model a pair of two values of types K and V
 */
public class MapEntry<K,V>{
    // Data members
    private K key;
    private V value;
    /**
     * Constructor
     * @param key the initial value of the data member key
     * @param value the initial value of the data member value
     */
    public MapEntry(K key, V value){
        this.key = key;
        this.value = value;
    }
    /**
     * Getter for key
     * @return the reference to the member key
     */
    public K getKey(){ return key;}
    /**
     * Getter for value
     * @return the reference to the member value
     */
    public V getValue(){ return value;}
    /**
     * Setter for key
     * @param key the new value of the member key
     */
    public void setKey(K key){ this.key = key;}
    /**
     * Setter for value
     * @param value the new value of the member value
     */
    public void setValue(V value){ this.value = value;}

    /**
     * toString method
     * @return a formatted string with the contents of the members key and value 
     * @override toString() from class Object
     */
    public String toString(){
        if(key != null && value != null)
            return "(" + key.toString() + ", " + value.toString() + ")";
        else if(key == null){
            return "( null, " + value.toString() + ")";
        } 
        else if(value == null){
            return "(" + key.toString() + ", null)";
        }
        else{
            return "(null, null)";
        }
    }
    /**
     * equals method
     * @param obj the object mapEntry being compared to this mapEntry
     * @return true if the two pairs are equal, false otherwise
     * @override equals(Object) from class Object
     */
    
    public boolean equals(Object obj){
        if(obj instanceof MapEntry){
            MapEntry<K,V> p = (MapEntry) obj; // downcasting to MapEntry
            return this.key.equals(p.key);
        }
        return false;
    }
}