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

    // Static object to hold the question data
    object QuestionData {
        // List of questions with options and explanations
        val questions = listOf(
            // Define each question with text, options, correct answer index, and explanation
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

    // Variables to track the current question index and score
    private var currentQuestionIndex = 0
    private var score = 0
    private lateinit var progressBar: ProgressBar // ProgressBar for timing the question
    private lateinit var timer: CountDownTimer // Timer for the question duration

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Retrieve arguments passed from previous fragments
        val args = arguments?.let { QuestionFragmentArgs.fromBundle(it) }
        currentQuestionIndex = args?.currentQuestionIndex ?: 0 // Get current question index
        score = args?.score ?: 0 // Get the current score

        // Initialize the ProgressBar
        progressBar = view.findViewById(R.id.progressBar)

        // Load the current question
        loadQuestion(view)

        // Set click listener for the submit button
        view.findViewById<Button>(R.id.submitButton).setOnClickListener {
            val selectedOptionIndex = getSelectedOptionIndex(view) // Get the selected option index
            if (selectedOptionIndex != -1) { // Ensure an option was selected
                timer.cancel() // Stop the timer when the answer is submitted
                val isCorrect = (selectedOptionIndex == QuestionData.questions[currentQuestionIndex].correctAnswerIndex) // Check if the answer is correct
                val correctAnswer = QuestionData.questions[currentQuestionIndex].options[QuestionData.questions[currentQuestionIndex].correctAnswerIndex] // Get the correct answer text
                if (isCorrect) {
                    score += 1 // Increment the score if the answer is correct
                }
                // Create an action to navigate to the AnswerFragment with the necessary arguments
                val action = QuestionFragmentDirections.actionQuestionFragmentToAnswerFragment(
                    isCorrect = isCorrect,
                    correctAnswer = correctAnswer,
                    currentQuestionIndex = currentQuestionIndex,
                    score = score // Pass the updated score
                )
                findNavController().navigate(action) // Navigate to the next fragment
            }
        }
    }

    private fun loadQuestion(view: View) {
        // Load question data based on the current question index
        if (currentQuestionIndex < QuestionData.questions.size) {
            val question = QuestionData.questions[currentQuestionIndex]
            view.findViewById<TextView>(R.id.questionTextView).text = question.text // Display the question text
            val optionsRadioGroup = view.findViewById<RadioGroup>(R.id.optionsRadioGroup)

            // Create a list of RadioButtons for the options
            val optionsButtons = listOf(
                view.findViewById(R.id.option1RadioButton),
                view.findViewById(R.id.option2RadioButton),
                view.findViewById(R.id.option3RadioButton),
                view.findViewById<RadioButton>(R.id.option4RadioButton)
            )

            // Set the text for each RadioButton from the question options
            for (i in optionsButtons.indices) {
                optionsButtons[i].text = question.options[i]
            }

            // Reset the progress bar
            progressBar.progress = 0
            startProgressBarTimer(view) // Start the timer for the question duration
        }
    }

    private fun startProgressBarTimer(view: View) {
        val timerTextView = view.findViewById<TextView>(R.id.timerTextView)
        // Create a timer for 20 seconds for the question
        timer = object : CountDownTimer(20000, 100) { // 20 seconds
            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                // Update the progress bar based on remaining time
                val progress = ((20000 - millisUntilFinished) * 100 / 20000).toInt()
                progressBar.progress = progress

                // Update the timer text view
                timerTextView.text = "Remaining Time: ${millisUntilFinished / 1000} s"
            }

            @SuppressLint("SetTextI18n")
            override fun onFinish() {
                // Actions to perform when the timer finishes
                timerTextView.text = "¡Time Out!" // Notify user that time is up
                val action = QuestionFragmentDirections.actionQuestionFragmentToResultFragment(score) // Navigate to result fragment with current score
                findNavController().navigate(action)
            }
        }.start() // Start the timer
    }

    private fun getSelectedOptionIndex(view: View): Int {
        val optionsRadioGroup = view.findViewById<RadioGroup>(R.id.optionsRadioGroup)
        val selectedId = optionsRadioGroup.checkedRadioButtonId // Get the ID of the selected RadioButton

        return when (selectedId) {
            R.id.option1RadioButton -> 0 // Option 1
            R.id.option2RadioButton -> 1 // Option 2
            R.id.option3RadioButton -> 2 // Option 3
            R.id.option4RadioButton -> 3 // Option 4
            else -> -1 // No option selected
        }
    }
}
