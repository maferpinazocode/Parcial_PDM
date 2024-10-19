package com.example.questionsanswers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import androidx.navigation.fragment.findNavController

class WelcomeFragment : Fragment(R.layout.fragment_welcome) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up the button to start the game
        view.findViewById<Button>(R.id.startButton).setOnClickListener {
            // Navigate to the QuestionFragment when the button is clicked
            findNavController().navigate(R.id.action_welcomeFragment_to_questionFragment)
        }
    }
}
