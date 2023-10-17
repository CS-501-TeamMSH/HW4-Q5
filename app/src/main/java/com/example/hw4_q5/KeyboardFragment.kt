package com.example.hw4_q5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class KeyboardFragment : Fragment() {

    private lateinit var hintbutton: Button
    private lateinit var hinttext: TextView
    private var isHintVisible: Boolean = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_keyboard, container, false)

        if (isHintVisible) {
            hinttext.setText(R.string.hinttext)
        }

        val buttons = mutableListOf<Button>()
        for (i in 'A'..'Z') {
            val buttonId = resources.getIdentifier("button$i", "id", requireContext().packageName)
            val button = view.findViewById<Button>(buttonId)
            buttons.add(button)
        }

        for (button in buttons) {
            button.setOnClickListener {
                handleButtonClick(button)
            }
        }

        hintbutton = view.findViewById(R.id.hintbutton)
        hinttext = view.findViewById(R.id.hinttext)
        hintbutton.setOnClickListener{showHint()}

        val playAgain = view.findViewById<Button>(R.id.playAgainButton)
        playAgain.setOnClickListener{reset(playAgain)}
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState != null) {
            isHintVisible = savedInstanceState.getBoolean("isHintVisible")
            // Restore more variables if needed
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putBoolean("isHintVisible", isHintVisible)
        // Save more variables if needed
    }

    private fun handleButtonClick(button: Button) {
        val letter = button.text.toString()[0]
        if (activity is MainActivity) {
            (activity as MainActivity).play(letter)
            disableButton(button)
        }

    }

    private fun showHint () {
        hinttext.setText(R.string.hinttext)
    }

    private fun reset(button: Button) {
        (activity as MainActivity).resetGame()
    }


    private fun disableButton(button: Button) {
        button.isClickable = false
    }


}
