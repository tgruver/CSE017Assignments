import java.util.Scanner;

/**
 * A recursive algorithm is an algorithm that breaks the problem into smaller subproblems and applies the same algorithm to solve the smaller subproblems.
 * 
 * Recursive mathmatical algorithms are factorial in length. For algo analysis, this is important: n!
 * 
 * Base Case: The stopping case for the recursive algorithm. It tells you under what condition to halt the execution of the recursive calls
 * 
 * Recursive case: Case that invokes self method 
 *
 * Stack Overflow: The number of times the method is called exceeds the size of the stack

 * 
 */


public class recursion {

    //iterative
    public static int fibonacci(int n) {
        int f1 = 1 ; int f = 0; int f2 = 1;
        if(n <= 2) return 1;
        while(n > 2){
            f = f1 + f2;
            f1 = f2; f2 = f;
            n=-1;
        }
        return f;
}


    //recursive
    public static int rFactorial(int n){
 public static int rFibonacci(int n){
    if(n <= 2)
        return 1;
    else{
        return rFibonacci(n-1) +
               rFibonacci(n-2);
    }
}



    //binary search
    public static void guessNumber(int lowVal, int highVal, Scanner scnr) {
        int midVal;            // Midpoint of low..high
        char userAnswer;       // User response

        midVal = (highVal + lowVal) / 2;

        // Prompt user for input
        System.out.print("Is it " + midVal + "? (l/h/y): ");
        userAnswer = scnr.next().charAt(0);

        if ((userAnswer != 'l') && (userAnswer != 'h')) { // Base case: found number
            System.out.println("Thank you!");
        }
        else {                                            // Recursive case: split into lower OR upper half
            if (userAnswer == 'l') {                       // Guess in lower half
                guessNumber(lowVal, midVal, scnr);            // Recursive call
            }
            else {                                         // Guess in upper half
                guessNumber(midVal + 1, highVal, scnr);           // Recursive call
            }
        }
    }


    //GCD
     /* Determine the greatest common divisor
      of two numbers, e.g. GCD(8, 12) = 4
   */
   public static int gcdCalculator(int inNum1, int inNum2) {
      int gcdVal;     // Holds GCD results

      if (inNum1 == inNum2) {   // Base case: Numbers are equal
         gcdVal = inNum1;       // Return value
      }
      else {                    // Recursive case: subtract smaller from larger
         if (inNum1 > inNum2) { // Call function with new values
            gcdVal = gcdCalculator(inNum1 - inNum2, inNum2);
         }
         else { // n1 is smaller
            gcdVal = gcdCalculator(inNum1, inNum2 - inNum1);
         }
      }

      return gcdVal;
   }

   //Overload the method to implement the recursion
    public static int rBinarySearch(int[] list, int key){
        return rBinarySearch(list, key, 0, list.length-1);
    }

    public static int rBinarySearch(int[] list, int key, int first, int last){
        if(first > last) return -1;                   // base case
        int middle = (first + last) / 2;
        if(key == list[middle]) return middle;        // found
        else if(key < list[middle]) last = middle -1;
        else first = middle + 1;
        return rBinarySearch(list, key, first, last); // recursion
    }


















   
   public static void main (String[] args) {
      Scanner scnr = new Scanner(System.in);
      int gcdInput1;     // First input to GCD calc
      int gcdInput2;     // Second input to GCD calc
      int gcdOutput;     // Result of GCD
   
      // Print program function   
      System.out.println("This program outputs the greatest \n" + 
                         "common divisor of two numbers.");
              
      // Prompt user for input
      System.out.print("Enter first number: ");
      gcdInput1 = scnr.nextInt();
      
      System.out.print("Enter second number: ");
      gcdInput2 = scnr.nextInt();
       
      // Check user values are > 1, call recursive GCD function
      if ((gcdInput1 < 1) || (gcdInput2 < 1)) {
         System.out.println("Note: Neither value can be below 1.");
      }
      else {
         gcdOutput = gcdCalculator(gcdInput1, gcdInput2);
         System.out.println("Greatest common divisor = " +  gcdOutput);
      }
   }
} 