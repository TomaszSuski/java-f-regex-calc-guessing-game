import java.util.Random;

public class GuessingGame {
    private final int randomNumber = new Random().nextInt(10) + 1;
    private int guessCounter = 0;

    public String guess(int guessedNumber) {
        guessCounter++;
        String triesSuffix = guessCounter == 1 ? "try" : "tries";
        String successMessage = String.format("You got it in %d %s", guessCounter, triesSuffix);
        String response = null;
        if (guessCounter == 4 && guessedNumber != getRandomNumber()) {
            response = String.format("You didn't get it and you've had %d %s. Game over.", guessCounter, triesSuffix);
        } else if (guessCounter > 4) {
            response =  "Sorry, you are limited to only 4 tries, your game is over.";
        } else {
            String accuracyText = null;
            if (guessedNumber < randomNumber) {
                accuracyText = "- you're too low";
            } else if (guessedNumber > randomNumber) {
                accuracyText = "- you're too high";
            }
            String looseMessage = String.format("You didn't get it %s", accuracyText);
            response = guessedNumber == getRandomNumber() ? successMessage : looseMessage;
        }
        return response;
    }

    public int getRandomNumber() {
        return randomNumber;
    }
}
