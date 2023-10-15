package com.example.hw4_q5

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    private lateinit var hangmanGame: HangmanGame
    private lateinit var word: String
    private lateinit var keyboardFragment: KeyboardFragment
    private lateinit var wordFragment: Word
    private lateinit var hangmanFragment: Hangman



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

            if (!wordFragment.wordView.text.toString().contains('_')) {
                Toast.makeText(this, "You won!", Toast.LENGTH_SHORT).show()
                val playAgainButton = findViewById<Button>(R.id.playAgainButton)
                playAgainButton.visibility = View.VISIBLE
            } else {
                var num = hangmanGame.getRemainingGuesses()
                hangmanFragment.setHangman(num)
                if (num == 0) {
                    Toast.makeText(this, "Game Over", Toast.LENGTH_SHORT).show()
                    val playAgainButton = findViewById<Button>(R.id.playAgainButton)
                    playAgainButton.visibility = View.VISIBLE
                }
            }
        }
    }

    fun playAgain(view: View) {
        // Reset the game or perform any necessary actions to start a new game.
        // For example, you can reset the word, hangman, and keyboardFragment.
        // Call the relevant reset functions or recreate the fragments and game state.

        // After resetting, you might want to hide the "Play Again" button again.
        val playAgainButton = findViewById<Button>(R.id.playAgainButton)
        playAgainButton.visibility = View.GONE
    }

    }