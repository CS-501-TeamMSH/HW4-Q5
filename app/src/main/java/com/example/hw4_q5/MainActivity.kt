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
    private lateinit var wordFragment: Word
    private lateinit var hangmanFragment: Hangman
    private var gameEnded = false
    private var hintButtonClicked = 0
    private var vowelsDisabled = false

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        hangmanGame = HangmanGame()

        keyboardFragment = KeyboardFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.keyboard, keyboardFragment)
            .commit()

        wordFragment = Word()
        supportFragmentManager.beginTransaction()
            .replace(R.id.word, wordFragment)
            .commit()

        hangmanFragment = Hangman()
        supportFragmentManager.beginTransaction()
            .replace(R.id.hangman, hangmanFragment)
            .commit()

        word = hangmanGame.selectRandomWord()
        Log.d("word,", word)

        val playAgainButton = findViewById<Button>(R.id.playAgainButton)
        playAgainButton.isEnabled = false


        playAgainButton.setOnClickListener() {
            playAgainButton.isEnabled = true
            hangmanGame = HangmanGame()
            wordFragment.resetDisplayedWord(word)
            hangmanFragment.resetHangman()

            // Generate a new random word
            word = hangmanGame.selectRandomWord()
            Log.d("word", word)

        }

        val showHintButton = findViewById<Button>(R.id.showHintButton)
    }
    fun showHint(view: View) {
        if (gameEnded) {
            Toast.makeText(this, "Game has ended. Press Play Again to start a new game.", Toast.LENGTH_SHORT).show()
            return
        }

        hintButtonClicked++

        when (hintButtonClicked) {
            1 -> showHintMessage()
            2 -> disableHalfLetters()
            3 -> revealVowels()
            else -> Toast.makeText(this, "Hint not available", Toast.LENGTH_SHORT).show()
        }
    }
    private fun showHintMessage() {
        Toast.makeText(this, "Hint: This is a hint message.", Toast.LENGTH_SHORT).show()
    }

    private fun disableHalfLetters() {
        if (vowelsDisabled) {
            Toast.makeText(this, "Vowels are already disabled.", Toast.LENGTH_SHORT).show()
        } else {
            // Implement code to disable half of the remaining letters not in the word
            // Be sure to disable all the vowel buttons
            disableVowelButtons()
            vowelsDisabled = true
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
    }




    fun play(char: Char) {
        if (hangmanGame.makeGuess(char)) {
            var indices = mutableListOf<Int>()
            word.forEachIndexed { index, c ->
                if (c == char) {
                    indices.add(index)
                }
            }

            for (index in indices) {
                wordFragment.updateDisplayedWord(char, word)
            }

            // Check if the game is won (all letters guessed)
            if (!wordFragment.wordView.text.toString().contains('_')) {
                Toast.makeText(this, "You won!", Toast.LENGTH_SHORT).show()
                gameEnded = true
                // Show the "Play Again" button and hide the "Show Hint" button
                findViewById<Button>(R.id.playAgainButton).visibility = View.VISIBLE
                findViewById<Button>(R.id.showHintButton).visibility = View.GONE

                // Disable all letter buttons when the game is won
                disableAllLetterButtons()
            }

        } else {
            var num = hangmanGame.getRemainingGuesses()
            hangmanFragment.setHangman(num)
            if (num == 0) {
                Toast.makeText(this, "Game Over", Toast.LENGTH_SHORT).show()
                gameEnded = true
                // Show the "Play Again" button and hide the "Show Hint" button
                findViewById<Button>(R.id.playAgainButton).visibility = View.VISIBLE
                findViewById<Button>(R.id.showHintButton).visibility = View.GONE
                // Disable all letter buttons when the game is over
                disableAllLetterButtons()
            }
        }
    }

    private fun disableAllLetterButtons() {
        val layout = findViewById<View>(R.id.keyboard) as ViewGroup
        for (i in 0 until layout.childCount) {
            val child = layout.getChildAt(i)
            if (child is Button) {
                child.isEnabled = false
            }
        }
    }

}
