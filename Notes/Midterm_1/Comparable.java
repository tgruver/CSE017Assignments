

/* 
compareTo used for natural ordering! 

Negative Number: obj1 comes before obj2.

Zero: obj1 is equal to obj2.

Positive Number: obj1 comes after obj2.

obj1.compareTo(obj2)
 */
public interface Comparable <E> {
    int compareTo(E obj);
}
