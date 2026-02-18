public class Exceptions {
    /*
    Exceptions allow you to handle errors in execution without ending programs. 
    
    A programmer can detect errors and throw exceptions to keep error-checking code separate from normal code and to reduce redundant error checks
    
    Understand the checked v. unchecked exceptions are defined by type of exception, some like io exception are checked while nullpointers are unchecked.

    Unchecked exceptions represent defects in the program's logic or "programming errors." The compiler does not force you to handle or declare them.
        Compiler Role: The compiler ignores these. You can write code that might throw an unchecked exception without a try-catch block, and the code will still compile.
    
    Checked exceptions are issues that a well-written application should anticipate and be able to recover from. The Java compiler enforces a "catch or specify" requirement for these.

        Compiler Role: The compiler checks these at compile-time. If you call a method that throws a checked exception, you must either wrap it in a try-catch block or declare it in your method signature using the throws keyword.

    
    */
        public static void main(String[] args){
        int x = 20;
        int k = 15/20;
        System.out.println(safeDivideCatch(x, k));

        try{System.out.println(safeDivideDeclare(x, k));} 
        catch(Exception e){ e.getMessage(); }
        finally{ System.out.println("finally block always executes reguardless of try catch outcome... unless code finishes execution in try catch block ");}
    }
    
    
    /* 
     *  
     * 
     *  try {
     *  
     *  if (...errorCheck...) 
     *      throw new Exception("Specific Error Message")
     * 
     *  ... code after check ...
     * } catch(SpecificException e){
     *       System.out.println("Specific Exception to use BEFORE general")
     *       System.out.println(e.getMessage());
     * } catch(Exception e2){
     *      System.out.println("General Exception to use AFTER specific")
     *      System.out.println(e2.getMessage())
     * }
     * 
     * 
     */


     // i can make new exceptions in new files -->
     public Exception_Handling (String message){
        super(message);
     }
     public Exception_Handling (){
        super("specific message here");
     } 

     
    //catch rule: The exception is thrown and caught inside the method
    
        public static double safeDivideCatch(double a, double b){ 
            try{
                if (b == 0)
                    throw new Exception();
                else
                    return (a/b);
            }
            catch(Exception e){
                return 0;
            }
        }
    //Declare Rule: The exception is thrown, but not caught. The method declares throwing the exception
    // A throws clause indicates that during runtime the corresponding method may exit unexpectedly due to an exception
    // Often used to hot potato a method to be handled in main method
    public static double safeDivideDeclare(int a, int b) throws Exception{
        if (b == 0)
            throw new Exception();
        else
        return (a/b);
    }

}
