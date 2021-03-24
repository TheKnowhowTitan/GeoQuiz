package com.bignerdranch.android.geoquiz

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

private const val TAG = "MainActivity"

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
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")
        setContentView(R.layout.activity_main)

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        previousButton = findViewById(R.id.previous_button)
        questionTextView = findViewById(R.id.question_text_view)

        trueButton.setOnClickListener {
            questionBank[currentIndex].isAnswered = true
            checkAnswer(true)
            displayScoreIfTestOver()
        }

        falseButton.setOnClickListener {
            questionBank[currentIndex].isAnswered = true
            checkAnswer(false)
            displayScoreIfTestOver()
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

    private fun displayScoreIfTestOver() {
        if (allQuestionsAreAnswered())
            Toast.makeText(this, getString(R.string.display_score, score), Toast.LENGTH_SHORT).show()
    }

    private fun allQuestionsAreAnswered(): Boolean {
        for (question in questionBank)
            if (!question.isAnswered)
                return false
        return true
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
            score += 1
            R.string.correct_toast
        } else {
            score -= 1
            R.string.incorrect_toast
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
        changeButtonState()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }
}