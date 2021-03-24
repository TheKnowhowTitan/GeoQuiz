package com.bignerdranch.android.geoquiz

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: ImageButton
    private lateinit var questionTextView: TextView
    private lateinit var previousButton: ImageButton

    private val questionBank = listOf(
            Question(R.string.question_australia, true, isAnswered = false),
            Question(R.string.question_oceans, true, isAnswered = false),
            Question(R.string.question_africa, false, isAnswered = false),
            Question(R.string.question_americas, true, isAnswered = false),
            Question(R.string.question_asia, true, isAnswered = false),
            Question(R.string.question_mideast, false, isAnswered = false)
    )

    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        previousButton = findViewById(R.id.previous_button)
        questionTextView = findViewById(R.id.question_text_view)

        trueButton.setOnClickListener {
            questionBank[currentIndex].isAnswered = true
            checkAnswer(true)
        }

        falseButton.setOnClickListener {
            questionBank[currentIndex].isAnswered = true
            checkAnswer(false)
        }

        nextButton.setOnClickListener {
            seeNextQuestion()
        }

        questionTextView.setOnClickListener {
            seeNextQuestion()
        }

        previousButton.setOnClickListener {
            currentIndex = (currentIndex - 1 + questionBank.size) % questionBank.size
            updateQuestion()
        }

        updateQuestion()
    }

    private fun seeNextQuestion() {
        currentIndex = (currentIndex + 1) % questionBank.size
        updateQuestion()
    }

    private fun changeButtonState() {
        trueButton.isEnabled = !questionBank[currentIndex].isAnswered
        falseButton.isEnabled = !questionBank[currentIndex].isAnswered
    }

    private fun updateQuestion() {
        questionTextView.setText(questionBank[currentIndex].textResId)
        changeButtonState()
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer

        val messageResId = if (userAnswer == correctAnswer) {
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
        changeButtonState()
    }
}