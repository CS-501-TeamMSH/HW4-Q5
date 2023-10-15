package com.example.hw4_q5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class KeyboardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_keyboard, container, false)

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

        return view
    }

    private fun handleButtonClick(button: Button) {
        val letter = button.text.toString()[0]
        if (activity is MainActivity) {
            (activity as MainActivity).play(letter)
        }
    }
}
