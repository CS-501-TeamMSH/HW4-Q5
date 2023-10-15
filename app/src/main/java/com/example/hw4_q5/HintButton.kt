package com.example.hw4_q5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView


class HintButton : Fragment() {
    lateinit var hintbutton: Button
    lateinit var hinttext: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_hint_button, container, false)
        hintbutton = view.findViewById(R.id.hintbutton)
        hinttext = view.findViewById(R.id.hinttext)
        return view
    }

    fun showHint () {
        hinttext.setText(R.string.hinttext)
    }

}