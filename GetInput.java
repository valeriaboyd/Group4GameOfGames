import java.util.InputMismatchException;
import java.util.Scanner;

public class GetInput {
    public int getInputInt() {
        Scanner scnr = new Scanner(System.in);
        try {
            return scnr.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Please re-enter valid integer: ");
            return -1;
        }
    }

    public String getInputStr() {
        Scanner scnr = new Scanner(System.in);
        return scnr.next();
    }

    public boolean isInRange(int input, int min, int max) {
        try {
            return (input >= min && input <= max);
        } catch (NumberFormatException e) {
            System.out.print("Please re-enter a valid integer: ");
            return false;
        }
    }
}
