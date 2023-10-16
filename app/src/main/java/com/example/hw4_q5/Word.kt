package com.example.hw4_q5

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class Word : Fragment() {

    private lateinit var wordView: TextView
    var hangmanGame: HangmanGame = HangmanGame()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_word, container, false)
        wordView = view.findViewById(R.id.wordText)
//        wordView.text = "_ _ _ _ _ _"
        initializeWord(hangmanGame.getWordLen())
        return view
    }

    fun initializeWord(wordLength: Int?) {
        val initialWord = "_ ".repeat(wordLength ?: 0)
        wordView.text = initialWord
    }

    fun updateDisplayedWord(char: Char, index: Int) {
        // Replace this later
        val currentText = wordView.text.toString()
        if (currentText == "_ _ _ _ _ _") {
            wordView.text = ""
        }
        if (index in currentText.indices) {
            val updatedText = StringBuilder(currentText)
            updatedText[index] = char
            wordView.text = updatedText.toString()
        }
    }

}