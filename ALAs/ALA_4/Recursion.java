import java.util.Scanner;

public class Recursion {
    public static int count(String str, char c){
        int counter = 0;
        for(int i=0; i<str.length();i++){
            if(str.charAt(i) == c){
                counter++;
            }
        }
        return counter;
        
    }

    public static int recursiveCount(String str, char c){
        if(str.length() == 0)
            return 0;
        
        if(str.charAt(0) == c) // then found 
            return 1 + recursiveCount(str.substring(1), c); //substring extracts from index 1 to end of string nigga
        return 0 + count(str.substring(1), c); // 0 bc no c found
    }

    public static void permutations(String s){
        for(int i = 0; i < s.length(); i ++){
            permutations("", s);
        }
    }



/**
 * 
 * @param s1 string where permutations are held
 * @param s2 string where remaining characters to undergo permutations are held
 * Helper Method
 */
    private static void permutations(String s1, String s2){
        if(s2.length() == 0){ // base case 
            System.out.println(s1);
            return;
        }

        for(int i = 0; i < s2.length(); i++){
            String newS2 = s2.substring(0, i) + s2.substring(i+1); //removes ith element nigga
            String newS1 = s1 + s2.charAt(i);
            permutations(newS1, newS2); 
        }

    }









    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter a string: ");
        String str = keyboard.nextLine();
        System.out.println("Enter a character: ");
        char c = keyboard.nextLine().charAt(0);
        System.out.println("The character " + c + " appears " + recursiveCount(str,c) + " times in \"" + str + "\"" );

        System.out.println("Enter a string: ");
        String str2 = keyboard.nextLine();
        permutations(str2);


    }
}

