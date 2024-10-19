package com.example.questionsanswers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController

class QuestionFragment : Fragment(R.layout.fragment_question) {
    private var currentQuestionIndex = 0
    private val questions = listOf(
        "¿Michael Jackson sang Thriller?",
        "¿Who was the president of the United States in 2020?",
        "¿Where is the Koala from?"
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val questionTextView: TextView = view.findViewById(R.id.questionTextView)
        val optionsRadioGroup: RadioGroup = view.findViewById(R.id.optionsRadioGroup)
        val nextButton: Button = view.findViewById(R.id.nextButton)

        // Set first question
        questionTextView.text = questions[currentQuestionIndex]

        nextButton.setOnClickListener {
            // Navigate to AnswerFragment to see if the answer is correct
            findNavController().navigate(R.id.action_questionFragment_to_answerFragment)
        }
    }
}
