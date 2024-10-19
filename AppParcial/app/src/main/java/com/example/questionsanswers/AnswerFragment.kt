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

        val feedbackTextView: TextView = view.findViewById(R.id.feedbackTextView)
        val nextQuestionButton: Button = view.findViewById(R.id.nextQuestionButton)

        feedbackTextView.text = "Tu respuesta es correcta!"

        nextQuestionButton.setOnClickListener {
            findNavController().navigate(R.id.action_answerFragment_to_questionFragment)
        }
    }
}

