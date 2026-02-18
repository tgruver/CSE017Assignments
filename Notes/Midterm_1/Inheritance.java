/*
   Abstract class- Common behavior between related subclasses

   - Abstract classes cannot be instantiated, but can be used as data type (polyphormism)
   
   - Abstract methods make the class abstract, but an abstract class may have no abstract methods

Accessibilities Modifiers: 
 - private	Accessible by self.
 - protected	Accessible by self, derived classes, and other classes in the same package.
 - public	Accessible by self, derived classes, and everyone else.
 - no specifier	Accessible by self and other classes in the same package.

 Overriding vs Overloading:
    Overriding:
       When a derived class defines a member method that has the same name and parameters as a base class's method, the member method is said to override the base class's method.When a derived class defines a member method that has the same name and parameters as a base class's method, the member method is said to override the base class's method.

    Overloading:
       In overloading, methods have the same name but different parameter types or number of parameters. The return type doesn't matter

*/
public class Inheritance{
   
   // Business.java
   public class Business {
      protected String name;
      protected String address;
      
      public String getDescription() {
         return name + " -- " + address;
      }
      //too lazy to write getters setters
   }

   // Restaurant.java
   public class Restaurant extends Business {
      private int rating;   
      @Override
      public String getDescription() {
         return name + " -- " + address +
            "\n  Rating: " + rating;
      }

      public String getDescriptionWithBaseClassInfoToo() {
         return super.getDescription() + "\n  Rating: " + rating;
   }
      //pretend setters here
   }

   public static void main(String[] args) {
         Boolean Override = false;
         Boolean Overload = false;
         if(Override){
            Restaurant favoritePlace = new Restaurant();
            favoritePlace.setName("Friends Cafe");
            favoritePlace.setAddress("500 W 2nd Ave");
            favoritePlace.setRating(5);
            System.out.println(favoritePlace.getDescription());      
         } else if(Overload){

         }
      }
}
