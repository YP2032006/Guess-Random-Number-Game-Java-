
import java.util.Random;
import java.util.Scanner;

//This is a simple java program that implements a guessing game.
public class GuessRandomNumberGame {
	
    private static final int MIN = 1;
    private static final int MAX = 100;
    private static final int MAX_ATTEMPTS = 10;
    private static Scanner scanner = new Scanner(System.in);

    // This is the main method that starts the game.
    public static void main(String[] args) {
        boolean keepPlaying = true;

        while (keepPlaying) {
            showMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    int randomNumber = generateRandomNumber(MIN, MAX);
                    playGame(randomNumber);
                    break;
                case 2:
                    showInstructions();
                    break;
                case 3:
                    System.out.println("Thank you for playing the Guess Random Number Game!");
                    keepPlaying = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, or 3.");
            }
        }

        scanner.close();
    }
    
    // This method generates a random number between 1 and 100.
    private static int generateRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    //This method handles the game logic.
    private static void playGame(int randomNumber) {
        int attempts = 0;
        boolean correctGuess = false;

        System.out.println("I have picked a number between 1 and 100. Try to guess it!");

        while (attempts < MAX_ATTEMPTS && !correctGuess) {
            System.out.print("Enter your guess: ");
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Discard invalid input
                continue;
            }

            int guess = scanner.nextInt();
            attempts++;

            if (guess < randomNumber) {
                System.out.println("Too low!");
            } else if (guess > randomNumber) {
                System.out.println("Too high!");
            } else {
                System.out.println("Congratulations! You guessed the number " + randomNumber + " in " + attempts + " attempts.");
                correctGuess = true;
            }
        }

        if (!correctGuess) {
            System.out.println("Game Over! You've used all your attempts.");
            System.out.println("The correct number was: " + randomNumber);
        }

        showPlayAgainMsg();
    }

    //This method displays the instructions for the game.
    private static void showInstructions() {
        System.out.println("\n--- Instructions ---");
        System.out.println("1. The computer will pick a random number between 1 and 100.");
        System.out.println("2. You have 10 attempts to guess the number.");
        System.out.println("3. After each guess, you'll be told if your guess is too high or too low.");
        System.out.println("4. Try to guess the number in as few attempts as possible!");
        System.out.println("---------------------\n");
    }

    // This method displays the main menu and prompts the user for input.
    private static void showMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. Play Game");
        System.out.println("2. Instructions");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
    }

    // This method gets the user's choice from the menu.
    private static int getUserChoice() {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number (1-3).");
            scanner.next(); 
            showMenu();
        }
        return scanner.nextInt();
    }

    // This method prompts the user to play again or exit.
    private static void showPlayAgainMsg() {
        System.out.print("Do you want to play again? (yes/no): ");
        scanner.nextLine();
        String response = scanner.nextLine().trim().toLowerCase();

        if (response.equals("yes")) {
            int newRandomNumber = generateRandomNumber(MIN, MAX);
            playGame(newRandomNumber);
        } else {
            System.out.println("Thanks for playing! Goodbye!");
        }
    }
}