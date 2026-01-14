
package pac1;
import java.util.*;


public class Lab9 {

    public static String performOperation(String input, int choice) {
        switch (choice) {
            case 1:
                return input + input;

            case 2:
                StringBuilder sb1 = new StringBuilder(input);
                for (int i = 0; i < sb1.length(); i++) {
                    if (i % 2 != 0) {
                        sb1.setCharAt(i, '#');
                    }
                }
                return sb1.toString();

            case 3:
                StringBuilder sb2 = new StringBuilder();
                Set<Character> seen = new HashSet<>();
                for (char c : input.toCharArray()) {
                    if (!seen.contains(c)) {
                        seen.add(c);
                        sb2.append(c);
                    }
                }
                return sb2.toString();

            case 4:
                
                StringBuilder sb3 = new StringBuilder(input);
                for (int i = 0; i < sb3.length(); i++) {
                    if (i % 2 != 0) { 
                        sb3.setCharAt(i, Character.toUpperCase(sb3.charAt(i)));
                    }
                }
                return sb3.toString();

            default:
                return "Invalid choice!";
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = sc.nextLine();
        System.out.println("Choose an operation:");
        System.out.println("1. Add the String to itself");
        System.out.println("2. Replace odd positions with #");
        System.out.println("3. Remove duplicate characters");
        System.out.println("4. Change odd characters to upper case");
        int choice = sc.nextInt();

        String result = performOperation(input, choice);
        System.out.println("Result: " + result);
    }
}
