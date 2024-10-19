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

    object QuestionData {
     val questions = listOf(
        Question(
            text = "¿Cuál fue el famoso apodo de Michael Jackson?",
            options = listOf("El Rey del Pop", "El Príncipe de la Música", "El Maestro del Baile", "El Rey del Soul"),
            correctAnswerIndex = 0,
            explanation = "Michael Jackson fue apodado 'El Rey del Pop' debido a su inmensa popularidad y éxito en la música pop."
        ),
        Question(
            text = "¿En qué década lanzó Michael Jackson su álbum 'Thriller'?",
            options = listOf("1960", "1970", "1980", "1990"),
            correctAnswerIndex = 2,
            explanation = "El álbum 'Thriller' fue lanzado en 1982 y se convirtió en el álbum más vendido de todos los tiempos."
        ),
        Question(
            text = "¿En qué película protagonizó Michael Jackson junto a Diana Ross?",
            options = listOf("The Wiz", "Beat It",  "Moonwalker", "Thriller"),
            correctAnswerIndex = 0,
            explanation = "Michael Jackson y Diana Ross protagonizaron 'The Wiz', una adaptación de 'El Mago de Oz'."
        ),
        Question(
            text = "¿Cuál de estos álbumes de Michael Jackson no fue un éxito comercial?",
            options = listOf("Thriller", "Bad", "Dangerous", "Invincible"),
            correctAnswerIndex = 3,
            explanation = "'Invincible' fue el álbum menos exitoso de Michael Jackson en términos de ventas comparado con sus otros álbumes."
        ),
        Question(
            text = "¿Qué organización benéfica fundó Michael Jackson?",
            options = listOf("Heal the World Foundation", "Save the Children", "Unicef", "Red Cross"),
            correctAnswerIndex = 0,
            explanation = "Michael Jackson fundó la 'Heal the World Foundation' para ayudar a los niños y promover la paz en el mundo."
        )
    )
    }

    private var currentQuestionIndex = 0
    private var score = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = arguments?.let { QuestionFragmentArgs.fromBundle(it) }
        currentQuestionIndex = args?.currentQuestionIndex ?: 0
        score = args?.score ?: 0

        loadQuestion(view)

        view.findViewById<Button>(R.id.submitButton).setOnClickListener {
            val selectedOptionIndex = getSelectedOptionIndex(view)
            if (selectedOptionIndex != -1) {
                val isCorrect = (selectedOptionIndex == QuestionData.questions[currentQuestionIndex].correctAnswerIndex)
                val correctAnswer = QuestionData.questions[currentQuestionIndex].options[QuestionData.questions[currentQuestionIndex].correctAnswerIndex]
                if (isCorrect) {
                    score += 1
                }
                // Crear la acción pasando todos los argumentos necesarios
                val action = QuestionFragmentDirections.actionQuestionFragmentToAnswerFragment(
                    isCorrect = isCorrect,
                    correctAnswer = correctAnswer,
                    currentQuestionIndex = currentQuestionIndex,
                    score = score // Pasar la puntuación actualizada
                )
                findNavController().navigate(action)
            }
        }
    }

    private fun loadQuestion(view: View) {
        if (currentQuestionIndex < QuestionData.questions.size) {
            val question = QuestionData.questions[currentQuestionIndex]
            view.findViewById<TextView>(R.id.questionTextView).text = question.text
            val optionsRadioGroup = view.findViewById<RadioGroup>(R.id.optionsRadioGroup)

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
