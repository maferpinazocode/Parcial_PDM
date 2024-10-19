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
            text = "¿Cuál fue el famoso apodo de Michael Jackson?",
            options = listOf("El Rey del Pop", "El Príncipe de la Música", "El Maestro del Baile", "El Rey del Soul"),
            correctAnswerIndex = 0
        ),
        Question(
            text = "¿En qué década lanzó Michael Jackson su álbum 'Thriller', considerado uno de los más vendidos de la historia?",
            options = listOf("1960", "1970", "1980", "1990"),
            correctAnswerIndex = 2
        ),
        Question(
            text = "¿En qué película protagonizó Michael Jackson junto a Diana Ross?",
            options = listOf("The Wiz", "Beat It", "Moonwalker", "Thriller"),
            correctAnswerIndex = 0
        ),
        Question(
            text = "¿Cuál de estos álbumes de Michael Jackson no fue un éxito comercial?",
            options = listOf("Thriller", "Bad", "Dangerous", "Invincible"),
            correctAnswerIndex = 3
        ),
        Question(
            text = "¿Qué organización benéfica fundó Michael Jackson?",
            options = listOf("Heal the World Foundation", "Save the Children", "Unicef", "Red Cross"),
            correctAnswerIndex = 0
        ),
        Question(
            text = "¿Qué famosa actuación de Michael Jackson se hizo viral en los años 80?",
            options = listOf("Moonwalk", "Breakdance", "Running Man", "Electric Slide"),
            correctAnswerIndex = 0
        ),
        Question(
            text = "¿Cuál fue el primer álbum en solitario de Michael Jackson?",
            options = listOf("Off the Wall", "Thriller", "Bad", "Dangerous"),
            correctAnswerIndex = 0
        )
    )

    private var currentQuestionIndex = 0 // Para rastrear la pregunta actual
    private var score = 0 // Mantener la puntuación acumulada

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Recibe el índice de la pregunta actual y la puntuación acumulada desde los argumentos
        val args = arguments?.let { QuestionFragmentArgs.fromBundle(it) }
        currentQuestionIndex = args?.currentQuestionIndex ?: 0
        score = args?.score ?: 0 // Recuperar la puntuación actual

        loadQuestion(view)

        view.findViewById<Button>(R.id.submitButton).setOnClickListener {
            val selectedOptionIndex = getSelectedOptionIndex(view)
            if (selectedOptionIndex != -1) {
                val isCorrect = (selectedOptionIndex == questions[currentQuestionIndex].correctAnswerIndex)
                val correctAnswer = questions[currentQuestionIndex].options[questions[currentQuestionIndex].correctAnswerIndex]

                // Si la respuesta es correcta, incrementar la puntuación
                if (isCorrect) {
                    score++
                }

                // Comprobar si hay más preguntas
                if (currentQuestionIndex + 1 < questions.size) {
                    // Crear la acción pasando todos los argumentos necesarios
                    val action = QuestionFragmentDirections.actionQuestionFragmentToAnswerFragment(
                        isCorrect = isCorrect,
                        correctAnswer = correctAnswer,
                        currentQuestionIndex = currentQuestionIndex + 1, // Avanza a la siguiente pregunta
                        score = score // Pasar la puntuación actualizada
                    )
                    findNavController().navigate(action)
                } else {
                    // Si no hay más preguntas, ir al ResultFragment
                    val action = QuestionFragmentDirections.actionQuestionFragmentToResultFragment(score)
                    findNavController().navigate(action)
                }
            }
        }
    }

    private fun loadQuestion(view: View) {
        if (currentQuestionIndex < questions.size) {
            val question = questions[currentQuestionIndex]
            view.findViewById<TextView>(R.id.questionTextView).text = question.text

            // Configurar las opciones de respuesta
            val optionsButtons = listOf(
                view.findViewById<RadioButton>(R.id.option1RadioButton),
                view.findViewById<RadioButton>(R.id.option2RadioButton),
                view.findViewById<RadioButton>(R.id.option3RadioButton),
                view.findViewById<RadioButton>(R.id.option4RadioButton)
            )

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
            else -> -1
        }
    }
}
