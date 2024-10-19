package com.example.questionsanswers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController

class ResultFragment : Fragment(R.layout.fragment_result) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Retrieve the final score from the arguments passed to this fragment
        val args = arguments?.let { ResultFragmentArgs.fromBundle(it) }
        val finalScore = args?.score ?: 0 // Default to 0 if no score is provided

        // Display the final score in the designated TextView
        view.findViewById<TextView>(R.id.scoreTextView).text = getString(R.string.score_text, finalScore)

        // Set up a button to restart the game and navigate back to the QuestionFragment
        view.findViewById<Button>(R.id.restartButton).setOnClickListener {
            // Create an action to reset the question index and score
            val action = ResultFragmentDirections.actionResultFragmentToQuestionFragment(
                currentQuestionIndex = 0, // Reset the question index to the first question
                score = 0 // Reset the score to 0
            )
            findNavController().navigate(action) // Navigate to the QuestionFragment
        }

        // Set up a button to return to the start (WelcomeFragment)
        view.findViewById<Button>(R.id.goToStartButton).setOnClickListener {
            // Navigate to the WelcomeFragment
            findNavController().navigate(R.id.action_resultFragment_to_welcomeFragment)
        }

        // Set up a button to exit the application
        view.findViewById<Button>(R.id.exitButton).setOnClickListener {
            activity?.finishAffinity() // Close the application and clear the activity stack
        }
    }
}
