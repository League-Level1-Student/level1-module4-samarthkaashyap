package _05_fortune_teller;

import java.util.Random;
import java.util.Scanner;

public class Magic8Ball {

    public static void main(String[] args) {
        // 2. Make a variable that will hold a random number and put a random number into this variable using "new Random().nextInt(4)"
        int randomNumber = new Random().nextInt(4);

        // 3. Print out this variable
        System.out.println("Random number generated: " + randomNumber);

        // 4. Get the user to enter a question for the 8 ball
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ask a question for the Magic 8 Ball: ");
        String question = scanner.nextLine();

        // 5-8. Respond based on the random number generated
        if (randomNumber == 0) {
            System.out.println("Yes");
        } else if (randomNumber == 1) {
            System.out.println("No");
        } else if (randomNumber == 2) {
            System.out.println("Maybe you should ask Google?");
        } else if (randomNumber == 3) {
            System.out.println("I’m not sure, try again later.");
        }

        // Close the scanner
        scanner.close();
    }
}