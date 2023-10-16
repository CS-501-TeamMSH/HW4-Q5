package com.example.hw4_q5

import android.widget.Toast
import kotlin.random.Random


class HangmanGame() {
    private val totalGuesses = 6
    private val wordlist = arrayOf("APPLES", "BANANA", "CHERRY", "GRAPES", "ORANGES")
    var word: String? = null
    private lateinit var guessedIndices: BooleanArray
    private var allowedGuesses = 6
    private var remainingGuesses = 6
    private var usedLetters = ArrayList<Char>()

    init {
        allowedGuesses = totalGuesses
        remainingGuesses = allowedGuesses
        selectRandomWord()
    }

    fun selectRandomWord(): String {
        word = wordlist.random()
        guessedIndices = BooleanArray(word?.length ?: 0)
        return word.toString()
    }

    fun getWordLen() : Int?{
        return word?.length
    }

    fun isUsed(char: Char): Boolean {
        return usedLetters.contains(char)
    }

    fun makeGuess(char: Char): Boolean {
        val isCorrect = word?.contains(char) == true
        if (!isCorrect) {
            remainingGuesses--
            return false
        }
        usedLetters.add(char)
        return true
    }

    fun getAllowedGuesses(): Int {
        return allowedGuesses
    }

    fun getRemainingGuesses(): Int {
        return remainingGuesses
    }

}