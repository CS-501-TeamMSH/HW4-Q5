package com.example.hw4_q5

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class Word : Fragment() {
    lateinit var wordView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_word, container, false)
        wordView = view.findViewById(R.id.wordText)
        wordView.text = "_ _ _ _ _ _"
        return view
    }

    fun initializeWord(wordLength: Int) {
        val initialWord = "_".repeat(wordLength)
        wordView.text = initialWord
    }

    fun updateDisplayedWord(char: Char, word: String) {
        val updatedText = StringBuilder()
        for (i in 0 until word.length) {
            if (word[i] == char) {
                updatedText.append(char)
            } else {
                updatedText.append(wordView.text[i * 2])
            }
            if (i < word.length - 1) {
                updatedText.append(' ')
            }
        }

        wordView.text = updatedText.toString()
    }

    fun resetDisplayedWord(word: String) {

    }
}