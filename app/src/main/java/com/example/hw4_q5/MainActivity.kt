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
        playAgainButton.visibility = View.GONE

        // Landscape
        // val isLandscape = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
        // val layoutId = if (isLandscape) R.layout.activity_main_landscape else R.layout.activity_main_portrait
        // setContentView(layoutId)

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

                // Disable all letter buttons when the game is won
                disableAllLetterButtons()
            }
        } else {
            var num = hangmanGame.getRemainingGuesses()
            hangmanFragment.setHangman(num)
            if (num == 0) {
                Toast.makeText(this, "Game Over", Toast.LENGTH_SHORT).show()
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
