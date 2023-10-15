package com.example.hw4_q5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.example.hw4_q5.HangmanGame
import com.example.hw4_q5.Hangman
import com.example.hw4_q5.KeyboardFragment
import com.example.hw4_q5.Word




class MainActivity : AppCompatActivity() {

    private lateinit var hangmanGame: HangmanGame
    private lateinit var word: String
    private lateinit var keyboardFragment: KeyboardFragment
    private lateinit var wordFragment: Word
    private lateinit var hangmanFragment: Hangman



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
        // Landscape
        // val isLandscape = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
        // val layoutId = if (isLandscape) R.layout.activity_main_landscape else R.layout.activity_main_portrait
        // setContentView(layoutId)

    }

    fun play(char: Char) {
        if (hangmanGame.makeGuess(char)) {
            var index = word.indexOf(char)
            wordFragment.updateDisplayedWord(char, index)
        } else {
            var num = hangmanGame.getRemainingGuesses()
            hangmanFragment.setHangman(num)
            if (num == 0) {
                Toast.makeText(this, "Game Over", Toast.LENGTH_SHORT).show()
            }

        }
    }
}