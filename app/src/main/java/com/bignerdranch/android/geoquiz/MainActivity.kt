package com.bignerdranch.android.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: ImageButton
    private lateinit var questionTextView: TextView
    private lateinit var previousButton: ImageButton

    private val questionBank = listOf(
            Question(R.string.question_australia, true),
            Question(R.string.question_oceans, true),
            Question(R.string.question_africa, false),
            Question(R.string.question_americas, true),
            Question(R.string.question_asia, true),
            Question(R.string.question_mideast, false)
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

        trueButton.setOnClickListener {view: View ->
            checkAnswer(true)
        }

        falseButton.setOnClickListener {view: View ->
            checkAnswer(false)
        }

        nextButton.setOnClickListener {view: View? ->
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

    private fun updateQuestion() {
        questionTextView.setText(questionBank[currentIndex].textResId)
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer

        val messageResId = if(userAnswer == correctAnswer) {
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
    }
}