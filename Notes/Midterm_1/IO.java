
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Scanner;

public class IO{
    /*
     * When reading or opening files, we must handle the exception passed
     * It is a good idea to use the finally block to close files 
     * 
     * 
     * The InputStream class provides several read() methods for extracting bytes of data from an input source.
        * System.in is a inputstream 
     * 
     * A programmer often needs to extract integers or strings from an InputStream. Instead of directly reading bytes from System.in, a program typically uses the Scanner class, which augments System.in by automatically scanning and converting a sequence of bytes into the desired data type. 
     * 
     *    try{...open file... write in file using filewriter} catch{...potential exceptions...}
     *    finally{
     *      if (fileWriter != null) {
            fileWriter.println(valueSum);
            fileWriter.close();
            }
          }   
     */
     public static void main(String[] args) {
        Boolean fileProperties = false;
        Boolean writingFiles = false;
        Boolean readFiles = false;
        Boolean readStrings = false;
        Boolean writeStrings = false;
        Boolean stringProcessing = false;
        if(fileProperties){
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Enter file name: ");
            String filename = keyboard.next();
            File file = new File(filename);
        if (!file.exists()) {
        System.out.println("File not found"); System.exit(0);
        }
        if(file.isFile()) {
            System.out.println(filename + " is a file.");
            System.out.println("Size of the file: " + file.length() + " bytes");
        }
        if(file.isDirectory()) {
            System.out.println(filename + " is a folder.");
            File[] list = file.listFiles();
            System.out.println("There are " + list.length + " items in the folder");
        }
    }
        if(readFiles){
            File file = new File("students.txt"); // 1.1. creating a File object
            int count = 0;
            try {
                Scanner readFile = new Scanner(file); // 1.2. opening the file for reading
                System.out.println("File opened successfully.");
                while(readFile.hasNext()) {           // 2.1. check for the end of the file
                    String fname = readFile.next();     // 2.2. reading a string
                    String lname = readFile.next();     // 2.2. reading a string
                    int id = readFile.nextInt();        // 2.3. reading an integer
                    double gpa = readFile.nextDouble(); // 2.4. reading a real number
                    System.out.println("Student " + (count+1) + ": (" + fname + " " + lname +
                                    ", " + id + ", " + gpa + " )");
                    count++;

            /*
             * ALTERNATIVLY: 
             * import java.io.IOException;

public class InputStreamReader {
  public static void main (String[] args)
  throws IOException {
     int usrInput;

     // Read 1st byte
     usrInput = System.in.read();
     // Read 2nd byte
     usrInput = System.in.read();
     // Read 3rd byte
     usrInput = System.in.read();
     // Read 4th byte
     usrInput = System.in.read();
     // Read 5th byte (empty buffer)
     usrInput = System.in.read();
  }
             */
            }
        readFile.close();  // 3. closing the file
        System.out.println(count + " students read from the file.");
        }
        catch(FileNotFoundException e) {
        System.out.println("Cannot open file \"students.txt\"");
        }

    }
        if(writingFiles){
            
            File file = new File("numbers.txt"); // 1.1. creating a File object
                try {
                    PrintWriter writeFile = new PrintWriter(file); // 1.2. opening the file for writing
                    for(int i=0; i<1000; i++) {
                        writeFile.println(Math.random());  // 2. Writing to the file
                    }
                    writeFile.close(); // 3. closing the file
                }
                catch(FileNotFoundException e) {
                    System.out.println("Cannot write to file."); 
                }   
                        
                    }
        if(readStrings){
            Scanner scnr = new Scanner(System.in); // Input stream for standard input
      Scanner inSS = null;                   // Input string stream
      String lineString;                     // Holds line of text
      String firstName;                      // First name
      String lastName;                       // Last name
      int userAge;                           // Age
      boolean inputDone;                     // Flag to indicate next iteration

      inputDone = false;

      // Prompt user for input
      System.out.println("Enter \"firstname lastname age\" on each line");
      System.out.println("(\"Exit\" as firstname exits).\n");

      // Grab data as long as "Exit" is not entered
      while (!inputDone) {

         // Entire line into lineString
         lineString = scnr.nextLine();

         // Create new input string stream
         inSS = new Scanner(lineString);

         // Now process the line
         firstName = inSS.next();

         // Output parsed values
         if (firstName.equals("Exit")) {
            System.out.println("   Exiting.");

            inputDone = true;
         }
         else {
            lastName = inSS.next();
            userAge = inSS.nextInt();

            System.out.println("   First name: " + firstName);
            System.out.println("   Last name: " + lastName);
            System.out.println("   Age: " + userAge);
            System.out.println();
         }
      }
    }
        if(writeStrings){
                 Scanner scnr = new Scanner(System.in);

     // Basic character stream for age
     StringWriter ageStream = new StringWriter();
     // Augments character stream with print()
     PrintWriter ageOSS = new PrintWriter(ageStream);

     int userAge;           // Age (int)

     // Gets user's age from input
     userAge = scnr.nextInt();

     // Writes userAge as characters to
     // output string stream buffer (PrintWriter)
     ageOSS.print(userAge);

     // Appends " (minor)" to stream buffer
     // if the user's age is less than 21
     if (userAge < 21) {
        ageOSS.print(" (minor)");
     }

     // Copies the stream's buffer to a String
     // and outputs string to the screen
     System.out.println("User's age: " + ageStream.toString());
        }
        if(stringProcessing){
           /*
           A programmer can read data from a string instead of from the keyboard (standard input) by associating a Scanner object with a String. A Scanner object initialized from a String is often referred to as an input string stream.

            The statement Scanner inSS = new Scanner(userInfo); creates a new Scanner object with a copy of the String userInfo. Then, the program can extract data from the Scanner inSS stream using methods like next(), nextInt(), nextDouble(), etc.
           */ 

                Scanner scnr = new Scanner(System.in); // Input stream for standard input
            Scanner inSS = null;                   // Input string stream
            String lineString;                     // Holds line of text
            String firstName;                      // First name
            String lastName;                       // Last name
            int userAge;                           // Age
            boolean inputDone;                     // Flag to indicate next iteration

            inputDone = false;

            // Prompt user for input
            System.out.println("Enter \"firstname lastname age\" on each line");
            System.out.println("(\"Exit\" as firstname exits).\n");

            // Grab data as long as "Exit" is not entered
            while (!inputDone) {

                // Entire line into lineString
                lineString = scnr.nextLine();

                // Create new input string stream
                inSS = new Scanner(lineString);

                // Now process the line
                firstName = inSS.next();

                // Output parsed values
                if (firstName.equals("Exit")) {
                    System.out.println("   Exiting.");

                    inputDone = true;
                }
                else {
                    lastName = inSS.next();
                    userAge = inSS.nextInt();

                    System.out.println("   First name: " + firstName);
                    System.out.println("   Last name: " + lastName);
                    System.out.println("   Age: " + userAge);
                    System.out.println();
                }
            }
        }
    }
        
}

/* */