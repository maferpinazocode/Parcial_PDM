package com.example.questionsanswers

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController

class QuestionFragment : Fragment(R.layout.fragment_question) {

    object QuestionData {
        val questions = listOf(
            Question(
                text = "What was Michael Jackson's famous nickname?",
                options = listOf("The King of Pop", "The Prince of Music", "The Dance Master", "The King of Soul"),
                correctAnswerIndex = 0,
                explanation = "Michael Jackson was nicknamed 'The King of Pop' due to his immense popularity and success in pop music."
            ),
            Question(
                text = "In which decade did Michael Jackson release his album 'Thriller'?",
                options = listOf("1960s", "1970s", "1980s", "1990s"),
                correctAnswerIndex = 2,
                explanation = "The album 'Thriller' was released in 1982 and became the best-selling album of all time."
            ),
            Question(
                text = "In which movie did Michael Jackson star alongside Diana Ross?",
                options = listOf("The Wiz", "Beat It", "Moonwalker", "Thriller"),
                correctAnswerIndex = 0,
                explanation = "Michael Jackson and Diana Ross starred in 'The Wiz', an adaptation of 'The Wizard of Oz'."
            ),
            Question(
                text = "Which of these Michael Jackson albums was not a commercial success?",
                options = listOf("Thriller", "Bad", "Dangerous", "Invincible"),
                correctAnswerIndex = 3,
                explanation = "'Invincible' was Michael Jackson's least successful album in terms of sales compared to his other albums."
            ),
            Question(
                text = "Which charity did Michael Jackson found?",
                options = listOf("Heal the World Foundation", "Save the Children", "Unicef", "Red Cross"),
                correctAnswerIndex = 0,
                explanation = "Michael Jackson founded the 'Heal the World Foundation' to help children and promote world peace."
            )
        )
    }

    private var currentQuestionIndex = 0
    private var score = 0
    private lateinit var progressBar: ProgressBar
    private lateinit var timer: CountDownTimer

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = arguments?.let { QuestionFragmentArgs.fromBundle(it) }
        currentQuestionIndex = args?.currentQuestionIndex ?: 0
        score = args?.score ?: 0

        progressBar = view.findViewById(R.id.progressBar)

        loadQuestion(view)

        view.findViewById<Button>(R.id.submitButton).setOnClickListener {
            val selectedOptionIndex = getSelectedOptionIndex(view)
            if (selectedOptionIndex != -1) {
                timer.cancel()
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
                view.findViewById(R.id.option1RadioButton),
                view.findViewById(R.id.option2RadioButton),
                view.findViewById(R.id.option3RadioButton),
                view.findViewById<RadioButton>(R.id.option4RadioButton)
            )

            for (i in optionsButtons.indices) {
                optionsButtons[i].text = question.options[i]
            }

            // Reiniciar la barra de progreso
            progressBar.progress = 0
            startProgressBarTimer(view) // Llama a la función del temporizador
        }
    }

    private fun startProgressBarTimer(view: View) {
        val timerTextView = view.findViewById<TextView>(R.id.timerTextView)
        // Temporizador de 20 segundos para la barra de progreso
        timer = object : CountDownTimer(20000, 100) { // 20 segundos
            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                // Actualizar la barra de progreso
                val progress = ((20000 - millisUntilFinished) * 100 / 20000).toInt()
                progressBar.progress = progress

                timerTextView.text = "Remaining Time: ${millisUntilFinished / 1000} s"
            }

            @SuppressLint("SetTextI18n")
            override fun onFinish() {
                timerTextView.text = "¡Time Out!"
                val action = QuestionFragmentDirections.actionQuestionFragmentToResultFragment(score)
                findNavController().navigate(action)
            }
        }.start()
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
