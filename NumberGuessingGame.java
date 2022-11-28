import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

  public static void main(String[] args) {

    Random rand = new Random();
    Scanner scanner = new Scanner(System.in);
    
    int randomNumber = rand.nextInt(100) + 1;
  
    
    int tryCount = 0;
    while(true) {
      System.out.println("Enter your guess Number (1-100):");
      
      int playerGuess = scanner.nextInt();
      tryCount++;
      
      if (playerGuess == randomNumber) {
        System.out.println("Correct! You Win and guessed correct Number!");
        System.out.println("It took you " + tryCount + " tries");
        break;
      }
      else if(randomNumber > playerGuess) {
        System.out.println("Nope! The number is higher than your guessed number. Guess again.");
      }
      else {
        System.out.println("Nope! The number is lower than your guessed number. Guess again.");
      }
    }
    scanner.close();
    
  }
}
