package com.bignerdranch.android.geoquiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import kotlin.properties.Delegates

const val EXTRA_ANSWER_SHOWN = "com.bignerdranch.android.geoquiz.answer_shown"
private const val EXTRA_ANSWER_IS_TRUE = "com.bignerdranch.android.geoquiz.answer_is_true"
private const val KEY_USER_CHEAT_STATUS = "USER_HAS_CHEATED"

class CheatActivity : AppCompatActivity() {

    private var answerIsTrue = false
    private var userHasCheated = false

    private lateinit var answerTextView: TextView
    private lateinit var showAnswerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)

        userHasCheated = savedInstanceState?.getBoolean(KEY_USER_CHEAT_STATUS, false) ?: false

        answerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)
        answerTextView = findViewById(R.id.answer_text_view)
        showAnswerButton = findViewById(R.id.show_answer_button)

        updateAnswerText()

        showAnswerButton.setOnClickListener {
            userHasCheated = true
            updateAnswerText()
            setAnswerShownResult(userHasCheated)
        }
        setAnswerShownResult(userHasCheated)
    }

    private fun updateAnswerText() {
        val answerText = when {
            !userHasCheated -> R.string.no_text
            answerIsTrue -> R.string.true_button
            else -> R.string.false_button
        }

        answerTextView.setText(answerText)
    }

    private fun setAnswerShownResult(isAnswerShown: Boolean) {
        Log.i("CheatActivity", "userHasCheated = $userHasCheated")
        val data = Intent().apply {
            putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown)
        }
        setResult(Activity.RESULT_OK, data)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i("CheatActivity", "userHasCheated = $userHasCheated")
        outState.putBoolean(KEY_USER_CHEAT_STATUS, userHasCheated)
    }

    companion object {
        fun newIntent(packageContext: Context, answerIsTrue: Boolean): Intent {
            return Intent(packageContext, CheatActivity::class.java).apply {
                putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
            }
        }
    }
}