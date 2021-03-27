package com.bignerdranch.android.geoquiz

import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"

class QuizViewModel : ViewModel() {

    private val questionBank = listOf(
        Question(R.string.question_australia, answer = true, isAnswered = false),
        Question(R.string.question_oceans, answer = true, isAnswered = false),
        Question(R.string.question_africa, answer = false, isAnswered = false),
        Question(R.string.question_americas, answer = true, isAnswered = false),
        Question(R.string.question_asia, answer = true, isAnswered = false),
        Question(R.string.question_mideast, answer = false, isAnswered = false)
    )

    var currentIndex = 0

    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer

    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId

    val currentAnsweredState: Boolean
        get() = questionBank[currentIndex].isAnswered

    var score = 0

    fun moveToNext() {
        currentIndex = (currentIndex + 1) % questionBank.size
    }

    fun moveToPrevious() {
        currentIndex = (currentIndex - 1 + questionBank.size) % questionBank.size
    }

    fun questionIsAnswered() {
        questionBank[currentIndex].isAnswered = true
    }

    fun increaseScore() {
        score += 1
    }

}