package com.example.hw4_q5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class Hangman : Fragment() {

    lateinit var wordView: TextView

    private var currentImageResource: Int = R.drawable.hangman1
    private var displayedWord: String = "_ _ _ _ _ _"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState != null) {
            currentImageResource = savedInstanceState.getInt("currentImageResource")
            displayedWord = savedInstanceState.getString("displayedWord") ?: "_ _ _ _ _ _"
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_hangman, container, false)
        val hangmanImageView = view.findViewById<ImageView>(R.id.hangman)
        hangmanImageView.setImageResource(currentImageResource)
        wordView = view.findViewById(R.id.wordText)
        wordView.text = displayedWord
        return view
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt("currentImageResource", currentImageResource)
        outState.putString("displayedWord", displayedWord)
    }

    fun setHangman(imageNumber: Int) {
        currentImageResource = when (imageNumber) {
            5 -> R.drawable.hangman2
            4 -> R.drawable.hangman3
            3 -> R.drawable.hangman4
            2 -> R.drawable.hangman5
            1 -> R.drawable.hangman6
            0 -> R.drawable.hangman7
            else -> R.drawable.hangman1
        }
        val hangmanImageView = view?.findViewById<ImageView>(R.id.hangman)
        hangmanImageView?.setImageResource(currentImageResource)
    }

    fun initializeWord(wordLength: Int) {
        displayedWord = "_ ".repeat(wordLength)
        wordView.text = displayedWord
    }

    fun updateDisplayedWord(char: Char, word: String) {
        val currentText = wordView.text.toString()
        val updatedText = StringBuilder()

        for (i in word.indices) {
            if (word[i] == char) {
                updatedText.append(char)
            } else {
                updatedText.append(currentText[i])
            }
        }

        displayedWord = updatedText.toString()
        wordView.text = displayedWord
    }
}
