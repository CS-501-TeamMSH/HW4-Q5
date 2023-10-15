package com.example.hw4_q5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hw4_q5.HangmanGame

class MainActivity : AppCompatActivity() {

    private lateinit var hangmanGame: HangmanGame

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        hangmanGame = HangmanGame()

        supportFragmentManager.beginTransaction()
            .replace(R.id.keyboard, KeyboardFragment())
            .commit()

        // Landscape
        // val isLandscape = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
        // val layoutId = if (isLandscape) R.layout.activity_main_landscape else R.layout.activity_main_portrait
        // setContentView(layoutId)

    }

    fun play(char: Char) {
        if (hangmanGame.makeGuess(char)) {
            return
        }

    }
}