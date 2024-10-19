package com.example.questionsanswers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import android.app.Activity

class ResultFragment : Fragment(R.layout.fragment_result) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Obtener el puntaje final de los argumentos
        val args = arguments?.let { ResultFragmentArgs.fromBundle(it) }
        val finalScore = args?.score ?: 0

        // Mostrar el puntaje final
        view.findViewById<TextView>(R.id.scoreTextView).text = getString(R.string.score_text, finalScore)

        // Botón para reiniciar el juego y volver a QuestionFragment
        view.findViewById<Button>(R.id.restartButton).setOnClickListener {
            val action = ResultFragmentDirections.actionResultFragmentToQuestionFragment(
                currentQuestionIndex = 0, // Reiniciar el índice de preguntas
                score = 0 // Reiniciar la puntuación
            )
            findNavController().navigate(action)
        }

        // Botón para volver al inicio (WelcomeFragment)
        view.findViewById<Button>(R.id.goToStartButton).setOnClickListener {
            findNavController().navigate(R.id.action_resultFragment_to_welcomeFragment)
        }

        // Botón para salir de la aplicación
        view.findViewById<Button>(R.id.exitButton).setOnClickListener {
            activity?.finishAffinity() // Cierra la aplicación
        }
    }
}
