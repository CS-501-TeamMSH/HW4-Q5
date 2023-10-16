package com.example.hw4_q5

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


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
            showToast(getString(R.string.correct))
            var index = word.indexOf(char)
            wordFragment.updateDisplayedWord(char, index)
        } else {
            var num = hangmanGame.getRemainingGuesses()
            if (num == 0) {
                showToast(getString(R.string.gameover))
            } else {
                showToast(getString(R.string.incorrect))
            }
            hangmanFragment.setHangman(num)


        }
    }

    private fun showToast(string: String) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // Save your state here
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        // Restore your state here
    }


}
