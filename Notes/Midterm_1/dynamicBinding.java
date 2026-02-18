package Notes;


public class dynamicBinding {
  /*

  Overriding differs from overloading. In overloading, methods have the same name but different parameter types or number of parameters. The return type doesn't matter. In overriding, a derived class member method has the same name, parameter types, number of parameters, and return type as the base class member method. Overloading usually occurs within one class but may occur across a base class and derived class. For overridden functions, either the base or derived version of the function may be called from a derived class object. For the base class version, the function name is preceded with the keyword super for an instance function and with the base class name for a static function. For the derived class version, just the function name is needed. 

  
    * Noteworthy interfaces: Clonable and Comparable

     - Cloneable is called a marker interface
        - Used to mark a class that overrides the clone() method
        -Implementing Cloneable means overriding the method clone() from the class Object
    */

/* Given that clone() can be defined by you, there are two ways to define it -->
 *  A shallow copy creates a new object, but it copies the references (memory addresses) of the items inside it, not the actual items themselves. Primitives (int, boolean): These are fully copied. If you change the count in the copy, the original is safe. Objects (Arrays, ArrayLists, Custom Classes): Only the link is copied. Both the original and the copy point to the same object in memory. The Risk: If you modify a mutable object inside the copy (e.g., you change a book's author), that change also shows up in the original.
 *
 * 
 * A deep copy creates a new object and then recursively creates new copies of everything inside it. Everything is New: It creates a new Library, a new titles array, and new Book objects for every single book. Independence: The two objects are completely fully disconnected. You can burn down the "Copy Library," and the "Original Library" remains untouched.
 */

    /* 

    - Every object of a subclass is an instance of the super class
      
    If person is of type object
        Student and Employee of type person
            Faculty is of type employee
      
    - SYNTAX: Object o = new Student(); for dynamic binding          

- Upcasting is implicit:   
    Object o = new Student();

- Downcasting must be explicit:
    Object o = new Student(); 
    if(o instanceof Student) {
    Student s = (Student) o;
    }


     */

 /*
   Abstract class- Common behavior between related subclasses

   - Abstract classes cannot be instantiated, but can be used as data type (polyphormism)
   
   - Abstract methods make the class abstract, but an abstract class may have no abstract methods

  */

  /*
   Interface- Common behavior between unrelated classes

   -  The core idea behind an interface is to define a "can-do" relationship. It doesn't describe what an object is, but what it can do. For example, many unrelated objects can be "printable" or "flyable


   -Only: Java provides interfaces as another mechanism for programmers to state that a class adheres to rules defined by the interface. An interface can specify a set of abstract methods that an implementing class must override and define. In an interface, an abstract method does not need the abstract keyword in front of the method signature.

   -Methods in an interface are implicitly public and abstract.

   -Variables are implicitly public, static, and final (they are constants).

   */
 
}

class clones implements Cloneable{
    // clone using deep copy
public Object clone(){
  return new Student(this.getID(), 
                     this.getName(), 
                     this.getAddress(), 
 this.getPhone(), 
 this.getEmail(), 
 this.major);
}
// OR
// clone using shallow copy
public Object clone(){
  return this;
}

}


//important interfaces and examples of their use 
abstract class Person implements Comparable<Person>{

 // Class Person constructors and methods
 /**
  * Method compareTo
  * @param p Person object being compared to this object
  * @return integer indicating the order of the objects this and p
  *  0   if the id of this and p are identical
  *  > 0 if the id of this is greater than the id of p
  *  < 0 if the id of this is less than the id of p
  */
  public int compareTo(Person p){
    return this.id - p.id;
  }
 }

  class Test{
  public static void main(String[] args){
   Person[] people = new Person[3];
   // code to create the objects
   System.out.println("Original List");
   printArray(people);
   System.out.println("List sorted by id");
   java.util.Arrays.sort(people); // sorting by id
   printArray(people);
  }
}


// abstract class example
abstract class Animal {
    private String name;

    // Constructor
    public Animal(String name) {
        this.name = name;
    }

    // Concrete method - shared by all animals
    public void eat() {
        System.out.println(name + " is eating.");
    }

    // Abstract method - must be implemented by subclasses
    public abstract void makeSound();
}

// Concrete subclass

class Dog extends Animal {
    public Dog(String name) {
        super(name); // Call the abstract class's constructor
    }

    // Providing implementation for the abstract method
    @Override
    public void makeSound() {
        System.out.println("Woof!");
    }
}

// Concrete subclass
class Cat extends Animal {
    public Cat(String name) {
        super(name);
    }

    @Override
    public void makeSound() {
        System.out.println("Meow!");
    }
}
 
class abstractClass {
    public static void main(String[] args) {
        
        Animal myDog = new Dog("Buddy");
        myDog.eat(); // Output: Buddy is eating.
        myDog.makeSound(); // Output: Woof!
    }
}


//interfaces example
// Behavior 1: Flying
interface Flyable {
    void takeOff();
    void land();
}

// Behavior 2: Floating
interface Swimmable {
    void dock();
}

// This class implements BOTH interfaces
class Seaplane implements Flyable, Swimmable {

    @Override
    public void takeOff() {
        System.out.println("Seaplane is taking off from water...");
    }

    @Override
    public void land() {
        System.out.println("Seaplane is landing on water...");
    }

    @Override
    public void dock() {
        System.out.println("Seaplane is docking...");
    }
}

//see also static methods: 
interface MathConstants {
    // A static method
    static double getPi() {
        return 3.14159;
    }
}

//see also methods with implementations
 interface Drivable {
    void drive(); // abstract method

    // A default method provides a body
    default void turnOnRadio() {
        System.out.println("Radio is on.");
    }
}