/**
 * printf(format specifiers, format string) <- arguments
    *
    *  Format string: specifies the format of the text to print along with any number of   placeholders for printing values. 
    * 
    * Format specifier: specifies the type of value to print in its place
    * %c	char	Prints a single Unicode character
    * %d	int, long, short	Prints a decimal integer value.
    * %f	float, double	Prints a floating-point value.
    * %e	float, double	Prints a floating-point value in scientific notation.
    * %s	String	Prints the characters in a String variable or literal.
    * %%		Prints the "%" character.
    * %n		Prints the platform-specific new-line character
    * 
        * Sub-specifiers: Provides formatting options for a format specifier and are included between the % and format specifier character
            * FORMAT: %(flags)(width)(.precision)specifier 
                * STRINGS:
                    * Flags: ' - ' Left Aligns output given specified width, padding output with spaces. In this case, -20 pads mystring to by 20. Formatting = 10 chars, therefore 20-10 spaces are padded to the right
                        * printf("%-20s String", myString);
                          Formatting           String     
                    *
                    * Width: Specifies the minimum number of characters to print. In this case 20 characters is the minimum number of characters for myString. Since Formatting is less than 20 chars, it is padded 10 spaces to the left  
                        *  printf("%20s String", myString);
                                     Formatting String
                    * .precision: Specifies the maximum number of characters to print. If the string has more characters than the precision, the string will be truncated.
                        * printf("%.6s", myString);
                        *  Format
                * 
                * FLOATING POINTS have the same sub-specifers as STRINGS
                * Integers have the same sub-scpecifers except can print '+' by as a flag to denot positive
            *
 * 
 */