package com.example.hw4_q5

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var hangmanGame: HangmanGame
    private lateinit var word: String
    private lateinit var keyboardFragment: KeyboardFragment
    private lateinit var hangmanFragment: Hangman
    private var gameEnded = false
    private var hintButtonClicked = 0
    private var vowelsDisabled = false

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            word = savedInstanceState.getString("currentWord", "")
            gameEnded = savedInstanceState.getBoolean("gameEnded", false)
            val remainingGuesses = savedInstanceState.getInt("remainingGuesses", 6)
            hangmanGame.setRemainingGuesses(remainingGuesses)

            hangmanFragment.setHangman(remainingGuesses)
            //hangmanFragment.initializeWord(word)
        } else {
            hangmanGame = HangmanGame()
            word = hangmanGame.selectRandomWord()
        }

        keyboardFragment = KeyboardFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.keyboard, keyboardFragment)
            .commit()

        hangmanFragment = Hangman()
        supportFragmentManager.beginTransaction()
            .replace(R.id.hangman, hangmanFragment)
            .commit()

        val playAgainButton = findViewById<Button>(R.id.playAgainButton)
        playAgainButton.visibility = if (gameEnded) View.VISIBLE else View.GONE
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString("currentWord", word)
        outState.putBoolean("gameEnded", gameEnded)
        outState.putInt("remainingGuesses", hangmanGame.getRemainingGuesses())
        super.onSaveInstanceState(outState)
    }

    fun showHint () {
        hintButtonClicked++

        when (hintButtonClicked) {
            1 -> showHintMessage()
            2 -> disableHalfLetters()
            3 -> revealVowels()
            else -> Toast.makeText(this, "Hint not available", Toast.LENGTH_SHORT).show()
        }
    }
    private fun showHintMessage() {
        Toast.makeText(this, R.string.hinttext, Toast.LENGTH_SHORT).show()
    }
    private fun disableHalfLetters() {
        if (vowelsDisabled) {
            Toast.makeText(this, "Vowels are already disabled.", Toast.LENGTH_SHORT).show()
        } else {
            // Implement code to disable half of the remaining letters not in the word
            val letters = ('A'..'Z').map { it.toString() }
            val lettersNotInWord = letters.filter { letter -> !word.contains(letter) }
            val remainingLetters = lettersNotInWord.take(lettersNotInWord.size/2)

            for (letter in remainingLetters) {
                Log.d("letter",letter)
                val buttonId = resources.getIdentifier("button$letter", "id", packageName)
                Log.d("buttonId","id: $buttonId")
                val letterButton = findViewById<Button>(buttonId)
                keyboardFragment.disableButton(letterButton)
            }

//            // Be sure to disable all the vowel buttons
//            disableVowelButtons()
//            vowelsDisabled = true
        }
    }

    private fun revealVowels() {
        if (vowelsDisabled) {
            Toast.makeText(this, "Vowels are already disabled.", Toast.LENGTH_SHORT).show()
        } else {
            // Implement code to reveal all the vowels

            // Be sure to disable all the vowel buttons
            disableVowelButtons()
        }
    }

    private fun disableVowelButtons() {
        // Implement code to disable all vowel buttons
        val vowelButtonIds = listOf(R.id.buttonA, R.id.buttonE, R.id.buttonI, R.id.buttonO, R.id.buttonU)
        for (buttonId in vowelButtonIds) {
            val vowelButton = findViewById<Button>(buttonId)
            keyboardFragment.disableButton(vowelButton)
        }
    }

    fun play(char: Char) {
        if (!gameEnded) {
            if (hangmanGame.makeGuess(char)) {
                val indices = mutableListOf<Int>()
                word.forEachIndexed { index, c ->
                    if (c == char) {
                        indices.add(index)
                    }
                }

                for (index in indices) {
                    hangmanFragment.updateDisplayedWord(char, word)
                }

                if (!hangmanFragment.wordView.text.toString().contains('_')) {
                    gameEnded = true
                    Toast.makeText(this, "You won!", Toast.LENGTH_SHORT).show()
                }
            } else {
                val num = hangmanGame.getRemainingGuesses()
                hangmanFragment.setHangman(num)
                if (num == 0) {
                    gameEnded = true
                    Toast.makeText(this, "Game Over", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun resetGame() {
        hangmanFragment.setHangman(6)
        hangmanGame = HangmanGame()
        word = hangmanGame.selectRandomWord()
//        hangmanFragment.initializeWord(word)
        gameEnded = false
        val playAgainButton = findViewById<Button>(R.id.playAgainButton)
        playAgainButton.visibility = View.GONE
    }
}
