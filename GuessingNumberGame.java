package sam.space;
import java.util.Random;
import java.util.Scanner;

public class GuessingNumberGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Welcome message and prompt for player's name
        System.out.println("Welcome to the Guessing Number Game!");
        System.out.print("Please enter your name: ");
        String name = scanner.nextLine();
        
        // Generate a random 4-digit number
        String computerNumber = generateNumber();
        
        // Start timer
        long startTime = System.currentTimeMillis();
        
        System.out.println("\nHello, " + name + "! A 4-digit number has been generated. Try to guess it!");

        int attempts = 0;
        while (true) {
            System.out.print("\nEnter your guess (4 digits): ");
            String userGuess = scanner.nextLine();

            // Validate the user's guess
            if (userGuess.length() != 4 || !userGuess.matches("\\d{4}")) {
                System.out.println("Invalid input. Please enter a 4-digit number.");
                continue;
            }

            // Check the guess and provide feedback
            String feedback = getFeedback(computerNumber, userGuess);
            attempts++;

            // Check if the guess is correct (feedback should be "++++")
            if (feedback.length() == 4 && feedback.equals("++++")) {
                // End game when the correct number is guessed
                long endTime = System.currentTimeMillis();
                double timeTaken = (endTime - startTime) / 1000.0;
                System.out.println("\nCongratulations, " + name + "! You've guessed the correct number " + computerNumber + " in " + attempts + " attempts.");
                System.out.println("Time taken: " + timeTaken + " seconds.");
                break;
            } else {
                System.out.println("Feedback: " + feedback);
            }
        }

        scanner.close();
    }

    // Method to generate a random 4-digit number as a string
    public static String generateNumber() {
        Random rand = new Random();
        int number = rand.nextInt(9000) + 1000; // Ensure it's a 4-digit number
        return String.valueOf(number);
    }

    // Method to generate feedback based on the guess
    public static String getFeedback(String computerNumber, String userGuess) {
        String feedback = "";
        
        // Arrays to track which digits have been matched
        boolean[] computerUsed = new boolean[4];  // Track digits in computer's number that have been used
        boolean[] guessUsed = new boolean[4];     // Track digits in the guess that have been used
        
        // First pass: Check for exact matches (+)
        for (int i = 0; i < 4; i++) {
            if (userGuess.charAt(i) == computerNumber.charAt(i)) {
                feedback += "+";
                computerUsed[i] = true;
                guessUsed[i] = true;
            }
        }

        // Second pass: Check for digit matches in wrong positions (-)
        for (int i = 0; i < 4; i++) {
            if (!guessUsed[i]) {
                for (int j = 0; j < 4; j++) {
                    // Only consider unused digits in computer's number
                    if (!computerUsed[j] && userGuess.charAt(i) == computerNumber.charAt(j)) {
                        feedback += "-";
                        computerUsed[j] = true;  // Mark this digit as used
                        break;  // Once a match is found, no need to check further
                    }
                }
            }
        }

        return feedback;
    }
}


