package com.bignerdranch.android.geoquiz

import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"

class QuizViewModel : ViewModel() {

    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true),
        Question(R.string.question_mideast, false)
    )

    var currentIndex = 0
    var isCheater = false

    private val cheatStatus = MutableList(questionBank.size) { false }

    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer

    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId

    val currentQuestionCheatStatus: Boolean
        get() = cheatStatus[currentIndex]

    fun setCheatStatus(hasCheated: Boolean) {
        cheatStatus[currentIndex] = hasCheated
    }

    fun moveToNext() {
        currentIndex = (currentIndex + 1) % questionBank.size
    }

}