package com.example.hw4_q5

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
