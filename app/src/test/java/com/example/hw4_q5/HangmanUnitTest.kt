package com.example.hw4_q5
import org.junit.Assert.assertEquals
import org.junit.Test
class HangmanUnitTest {
    // Test 1: Test guess function when guessing correctly
    @Test
    fun testMakeGuessCorrect() {
        val hangmanGame = HangmanGame()
        hangmanGame.word = "ORANGES"
        val result = hangmanGame.makeGuess('E')

        assertEquals(true, result)
    }

    // Test 2: Test guess function when guessing incorrectly
    @Test
    fun testMakeGuessIncorrect() {
        val hangmanGame = HangmanGame()
        hangmanGame.word = "CHERRY"
        val result = hangmanGame.makeGuess('Q')

        assertEquals(false, result)
    }
}
