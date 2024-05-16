import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GuessingGameTest {
    private static final int GAME_RANDOMNESS_RETRIES = 100;
    private GuessingGame game;

    @BeforeEach
    void setUp() {
        game = new GuessingGame();
    }

    private void makeExplicitNumberOfWrongGuesses(int numOfGuesses) {
        for (int i = 0; i < numOfGuesses; i++) {
            game.guess(-3);
        }
    }

    @Test
    public void testSimpleWinSituation() {
        int generatedNumber = game.getRandomNumber();
        String message = game.guess(generatedNumber);
        assertEquals("You got it in 1 try", message);
    }

    @Test
    public void testOneWrongNegGuessSituation() {
        String message = game.guess(-5);
        assertEquals("You didn't get it - you're too low", message);
    }

    @Test
    public void testOneWrongPosSituation() {
        int generatedNumber = game.getRandomNumber();
        String message = game.guess(generatedNumber + 1);
        assertEquals("You didn't get it - you're too high", message);
    }

    @Test
    public void testRandomNumberGeneration() {
        int[] rndNumCount = new int[10];
        for (int i = 0; i < GAME_RANDOMNESS_RETRIES; i++) {
            GuessingGame newGame = new GuessingGame();
            int randomNum = newGame.getRandomNumber();
            rndNumCount[randomNum - 1] = 1;
        }

        int sum = 0;
        for (int rndNum: rndNumCount) {
            sum += rndNum;
        }
        assertEquals(10, sum);
    }

    @Test
    public void testFourWrongGuessesEndsTheGame() {
        makeExplicitNumberOfWrongGuesses(3);
        String msg = game.guess(-3);
        assertEquals("You didn't get it and you've had 4 tries. Game over.", msg);
    }

    @Test
    public void testTenWrongGuessesEndsTheGame() {
        makeExplicitNumberOfWrongGuesses(9);
        String msg = game.guess(-3);
        assertEquals("Sorry, you are limited to only 4 tries, your game is over.", msg);
    }

    @Test
    public void testFourWrongGuessesAndOneCorrect() {
        makeExplicitNumberOfWrongGuesses(4);
        int generatedNumber = game.getRandomNumber();
        String msg = game.guess(generatedNumber);
        assertEquals("Sorry, you are limited to only 4 tries, your game is over.", msg);
    }

    @Test
    public void testThreeWrongGuessesAndOneCorrect() {
        makeExplicitNumberOfWrongGuesses(3);
        int generatedNumber = game.getRandomNumber();
        String msg = game.guess(generatedNumber);
        assertEquals("You got it in 4 tries", msg);
    }

    @Test
    public void testTwoWrongGuessesAndOneCorrect() {
        makeExplicitNumberOfWrongGuesses(2);
        int generatedNumber = game.getRandomNumber();
        String msg = game.guess(generatedNumber);
        assertEquals("You got it in 3 tries", msg);
    }
}
