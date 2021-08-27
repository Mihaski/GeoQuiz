package com.bignerdrancho.android.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
//import android.view.Gravity
//import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
//import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

private const val TAG = "MainActivity"
private const val KEY_INDEX = "index"

class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button
    private lateinit var prevButton: Button
    private lateinit var questionTextView:TextView

    private val quizViewModel: QuizViewModel by lazy {
        ViewModelProviders.of(this).get(QuizViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate(Bundle?) called")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val currentIndex = savedInstanceState?.getInt(KEY_INDEX, 0) ?: 0
        quizViewModel.currentIndex = currentIndex

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        prevButton = findViewById(R.id.prev_button)

        questionTextView = findViewById(R.id.question_text_view)

        trueButton.setOnClickListener {
            /*Toast(this).setGravity(Gravity.TOP,0,0)
            Toast(this).show()*///хочу посмотреть как будет смещаться тост
            checkAnswer(true)
        }
        falseButton.setOnClickListener {
            checkAnswer(false)
        }
        nextButton.setOnClickListener {
            quizViewModel.moveToNext()
            updateQuestion()
        }
        prevButton.setOnClickListener {
            quizViewModel.moveToPrev()
            updateQuestion()
        }
        questionTextView.setOnClickListener {
            quizViewModel.moveToNext()
            updateQuestion()
        }
            updateQuestion()
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
    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        Log.i(TAG, "onSaveInstanceState")
        savedInstanceState.putInt(KEY_INDEX, quizViewModel.currentIndex)
    }
    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }

    private fun updateQuestion() {

        if (!quizViewModel.choice) {
            trueButton.isEnabled = true
            falseButton.isEnabled = true
        }
        else {
            trueButton.isEnabled = false
            falseButton.isEnabled = false
        }

        val questionTextResId = quizViewModel.currentQuestionText
        questionTextView.setText(questionTextResId)

    }

    private var percentageCorrect = 0

    private fun checkAnswer(userAnswer: Boolean) {
        trueButton.isEnabled = false
        falseButton.isEnabled = false
        quizViewModel.choice = true
        val correctAnswer = quizViewModel.currentQuestionAnswer
        percentageCorrect += if (userAnswer != correctAnswer) {
            0
        } else {
            1
        }
        val messageResId = if (userAnswer != correctAnswer) { getString(R.string.incorrect_toast) } else { getString(R.string.correct_toast) }
        Toast.makeText(this, "$messageResId $percentageCorrect/6", Toast.LENGTH_SHORT ) .show()

    }
}