package com.example.questionsanswers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController

class AnswerFragment : Fragment(R.layout.fragment_answer) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = AnswerFragmentArgs.fromBundle(requireArguments())
        val isCorrect = args.isCorrect
        val correctAnswer = args.correctAnswer
        val currentQuestionIndex = args.currentQuestionIndex // Índice actualizado

        val feedbackTextView: TextView = view.findViewById(R.id.feedbackTextView)
        val nextQuestionButton: Button = view.findViewById(R.id.nextQuestionButton)

        if (isCorrect) {
            feedbackTextView.text = getString(R.string.correct_text)
        } else {
            feedbackTextView.text = getString(R.string.incorrect_text, correctAnswer)
        }

        nextQuestionButton.setOnClickListener {
            if (currentQuestionIndex < 2) {
                val action = AnswerFragmentDirections.actionAnswerFragmentToQuestionFragment(
                    currentQuestionIndex = currentQuestionIndex
                )
                findNavController().navigate(action)
            } else {
                feedbackTextView.text = "¡Has completado todas las preguntas!"
                nextQuestionButton.isEnabled = false // Desactiva el botón
            }
        }
    }
}
