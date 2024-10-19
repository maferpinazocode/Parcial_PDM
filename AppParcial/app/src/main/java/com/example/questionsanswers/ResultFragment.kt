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

        val args = arguments?.let { ResultFragmentArgs.fromBundle(it) }
        val finalScore = args?.score ?: 0

        view.findViewById<TextView>(R.id.scoreTextView).text = getString(R.string.score_text, finalScore)

        view.findViewById<Button>(R.id.restartButton).setOnClickListener {
            // Reiniciar el juego al volver al QuestionFragment
            val action = ResultFragmentDirections.actionResultFragmentToQuestionFragment(
                currentQuestionIndex = 0, // Reiniciar el índice de preguntas
                score = 0 // Reiniciar la puntuación
            )
            findNavController().navigate(action)
        }
    }
}
