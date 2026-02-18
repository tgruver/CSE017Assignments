
public class regexAndStringBuilders{
    /*
     * String replaceFirst(String regex, String) 
       String replaceAll(String regex, String) 
       String[] split(String regex) 
       boolean matches(String regex)

      .*.java → Any zero or more characters followed by .java

        \\d{3}-\\d{2}-\\d{4} → 3 digits, dash, 2 digits, dash, and 4 digits

        [+#%] → Any of the characters +, #, or %

     * 
     */

     public static void main(String[] args) {
        System.out.println("2+3-5".replaceFirst("[+-]","%"));
        System.out.println("2+3-5".replaceAll("[+-]", "%")); 
        String[] items = "02/25/2021".split("/");
        for(String item: items){
            System.out.println(item + " ");
        } 
        String[] tokens = "Java,C?C#,C++".split("[.,:;?]");
        for(String token: tokens){
            System.out.println(token + " ");
        } 
        System.out.println("2+3-5".matches("\\d[+-]\\d[+-]\\d"));
        System.out.println("2+3-5".equals("\\d[+-]\\d[+-]\\d")); 
        System.out.println("440-02-4534".matches("\\d{3}-\\d{2}-\\d{4}"));
    }

}