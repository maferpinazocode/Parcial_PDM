package com.example.questionsanswers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.questionsanswers.QuestionFragment.QuestionData.questions

class AnswerFragment : Fragment(R.layout.fragment_answer) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Extract arguments passed to the fragment (correctness, correct answer, question index, and score)
        val args = AnswerFragmentArgs.fromBundle(requireArguments())
        val isCorrect = args.isCorrect
        val correctAnswer = args.correctAnswer
        val currentQuestionIndex = args.currentQuestionIndex
        val score = args.score // Get accumulated score

        // Setup the views for feedback and explanation
        val feedbackTextView: TextView = view.findViewById(R.id.feedbackTextView)
        val explanationTextView: TextView = view.findViewById(R.id.explanationTextView) // New TextView for explanation
        val nextQuestionButton: Button = view.findViewById(R.id.nextQuestionButton)

        // Provide feedback based on whether the answer was correct or not
        if (isCorrect) {
            feedbackTextView.text = getString(R.string.correct_text) // Show correct message
            feedbackTextView.setTextColor(resources.getColor(R.color.correct_answer)) // Set green color for correct
            explanationTextView.text = questions[currentQuestionIndex].explanation // Show explanation for the question
        } else {
            feedbackTextView.text = getString(R.string.incorrect_text, correctAnswer) // Show incorrect message
            feedbackTextView.setTextColor(resources.getColor(R.color.incorrect_answer)) // Set red color for incorrect
            explanationTextView.text = questions[currentQuestionIndex].explanation // Show explanation
        }

        // Handle button click to navigate to the next question or result
        nextQuestionButton.setOnClickListener {
            val nextQuestionIndex = currentQuestionIndex + 1 // Increment question index
            if (nextQuestionIndex < questions.size) { // Check if there are more questions
                val action = AnswerFragmentDirections.actionAnswerFragmentToQuestionFragment(
                    currentQuestionIndex = nextQuestionIndex, // Pass the new question index
                    score = score // Pass the updated score
                )
                findNavController().navigate(action) // Navigate to next question
            } else {
                val action = AnswerFragmentDirections.actionAnswerFragmentToResultFragment(score) // Go to result if no more questions
                findNavController().navigate(action) // Navigate to result fragment
            }
        }
    }
}
