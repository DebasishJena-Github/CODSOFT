import java.util.Random;
import java.util.Scanner;

public class NumberGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int totalAttempts = 0;
        int roundsPlayed = 0;
        int totalScore = 0;
        boolean playAgain;
        
        do {
            RoundResult result = playRound(scanner, random);
            totalScore += result.score;
            totalAttempts += result.attempts;
            roundsPlayed++;
            System.out.print("Do you want to play again? (yes/no): ");
            playAgain = scanner.next().trim().equalsIgnoreCase("yes");
        } while (playAgain);
        
        scanner.close();
        
        // Display user's score
        System.out.println("\nGame Over!");
        System.out.printf("Total rounds played: %d\n", roundsPlayed);
        System.out.printf("Total attempts: %d\n", totalAttempts);
        if (roundsPlayed > 0) {
            System.out.printf("Average attempts per round: %.2f\n", (double) totalAttempts / roundsPlayed);
        }
        System.out.printf("Total Score: %d\n", totalScore);
    }

    private static RoundResult playRound(Scanner scanner, Random random) {
        int numberToGuess = random.nextInt(100) + 1; // Random number between 1 and 100
        int attempts = 0;
        final int MAX_ATTEMPTS = 10;
        
        System.out.println("Guess the number between 1 and 100!");

        while (attempts < MAX_ATTEMPTS) {
            System.out.printf("Attempt %d/%d: ", attempts + 1, MAX_ATTEMPTS);
            int guess = scanner.nextInt();
            attempts++;
            
            if (guess < numberToGuess) {
                System.out.println("Too low!");
            } else if (guess > numberToGuess) {
                System.out.println("Too high!");
            } else {
                int score = calculateScore(attempts);
                System.out.printf("Congratulations! You guessed the number in %d attempts.\n", attempts);
                System.out.printf("Your score for this round: %d\n", score);
                return new RoundResult(attempts, score); // Return the number of attempts and score for this round
            }
        }
        
        System.out.printf("Sorry, you've used all %d attempts. The number was %d.\n", MAX_ATTEMPTS, numberToGuess);
        return new RoundResult(MAX_ATTEMPTS, 0); // Return max attempts and score 0 if not guessed correctly
    }
    
    private static int calculateScore(int attempts) {
        final int MAX_SCORE = 100;
        // Calculate score based on the number of attempts
        return Math.max(MAX_SCORE - (attempts - 1) * 10, 0); // Decrease score by 10 for each attempt beyond the first
    }
    
    // Helper class to store results for each round
    private static class RoundResult {
        int attempts;
        int score;
        
        RoundResult(int attempts, int score) {
            this.attempts = attempts;
            this.score = score;
        }
    }
}
