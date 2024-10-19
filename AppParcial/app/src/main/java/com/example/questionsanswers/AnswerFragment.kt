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

        val args = AnswerFragmentArgs.fromBundle(requireArguments())
        val isCorrect = args.isCorrect
        val correctAnswer = args.correctAnswer
        val currentQuestionIndex = args.currentQuestionIndex
        val score = args.score // Obtener la puntuación acumulada

        val feedbackTextView: TextView = view.findViewById(R.id.feedbackTextView)
        val explanationTextView: TextView = view.findViewById(R.id.explanationTextView) // Nueva TextView para la explicación
        val nextQuestionButton: Button = view.findViewById(R.id.nextQuestionButton)

        // Mostrar feedback basado en si la respuesta fue correcta o incorrecta
        if (isCorrect) {
            feedbackTextView.text = getString(R.string.correct_text)
            feedbackTextView.setTextColor(resources.getColor(R.color.correct_answer)) // Color verde para correcto
            explanationTextView.text = questions[currentQuestionIndex].explanation // Mostrar explicación
        } else {
            feedbackTextView.text = getString(R.string.incorrect_text, correctAnswer)
            feedbackTextView.setTextColor(resources.getColor(R.color.incorrect_answer)) // Color rojo para incorrecto
            explanationTextView.text = questions[currentQuestionIndex].explanation // Mostrar explicación
        }

        nextQuestionButton.setOnClickListener {
            val nextQuestionIndex = currentQuestionIndex + 1 // Incrementar el índice de la pregunta
            if (nextQuestionIndex < questions.size) { // Asegúrate de usar el tamaño correcto
                val action = AnswerFragmentDirections.actionAnswerFragmentToQuestionFragment(
                    currentQuestionIndex = nextQuestionIndex, // Pasar el nuevo índice de la pregunta
                    score = score // Pasar la puntuación acumulada
                )
                findNavController().navigate(action)
            } else {
                val action = AnswerFragmentDirections.actionAnswerFragmentToResultFragment(score)
                findNavController().navigate(action)
            }
        }
    }
}
