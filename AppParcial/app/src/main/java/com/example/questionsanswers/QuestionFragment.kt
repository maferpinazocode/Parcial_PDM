package com.example.questionsanswers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController

class QuestionFragment : Fragment(R.layout.fragment_question) {
    private val questions = listOf(
        Question(
            text = "¿Cuál es la capital de Francia?",
            options = listOf("Berlín", "Madrid", "París", "Lisboa"),
            correctAnswerIndex = 2
        ),
        Question(
            text = "¿Cuánto es 2 + 2?",
            options = listOf("3", "4", "5", "6"),
            correctAnswerIndex = 1
        )
    )

    private var currentQuestionIndex = 0 // Índice de la pregunta actual

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = arguments?.let { QuestionFragmentArgs.fromBundle(it) }
        currentQuestionIndex = args?.currentQuestionIndex ?: 0

        loadQuestion(view)

        view.findViewById<Button>(R.id.submitButton).setOnClickListener {
            val selectedOptionIndex = getSelectedOptionIndex(view)
            if (selectedOptionIndex != -1) {
                val isCorrect = (selectedOptionIndex == questions[currentQuestionIndex].correctAnswerIndex)
                val correctAnswer = questions[currentQuestionIndex].options[questions[currentQuestionIndex].correctAnswerIndex]

                val action = QuestionFragmentDirections.actionQuestionFragmentToAnswerFragment(
                    isCorrect = isCorrect,
                    correctAnswer = correctAnswer,
                    currentQuestionIndex = currentQuestionIndex + 1
                )
                findNavController().navigate(action)
            }
        }
    }

    private fun loadQuestion(view: View) {
        if (currentQuestionIndex < questions.size) {
            val question = questions[currentQuestionIndex]
            view.findViewById<TextView>(R.id.questionTextView).text = question.text
            val optionsRadioGroup = view.findViewById<RadioGroup>(R.id.optionsRadioGroup)

            val optionsButtons = listOf(
                view.findViewById(R.id.option1RadioButton),
                view.findViewById(R.id.option2RadioButton),
                view.findViewById(R.id.option3RadioButton),
                view.findViewById<RadioButton>(R.id.option4RadioButton)
            )

            // Asignar las opciones de la pregunta a los botones de radio
            for (i in optionsButtons.indices) {
                optionsButtons[i].text = question.options[i]
            }
        }
    }

    private fun getSelectedOptionIndex(view: View): Int {
        val optionsRadioGroup = view.findViewById<RadioGroup>(R.id.optionsRadioGroup)
        val selectedId = optionsRadioGroup.checkedRadioButtonId

        return when (selectedId) {
            R.id.option1RadioButton -> 0
            R.id.option2RadioButton -> 1
            R.id.option3RadioButton -> 2
            R.id.option4RadioButton -> 3
            else -> -1 // Ninguna opción seleccionada
        }
    }
}
