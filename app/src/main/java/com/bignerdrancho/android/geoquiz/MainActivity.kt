package com.bignerdrancho.android.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
// import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var questionTextView:TextView
    private val questionBank = listOf(
        Question(R.string.question_australia,true),
        Question(R.string.question_oceans,true),
        Question(R.string.question_mideast,false),
        Question(R.string.question_africa,false),
        Question(R.string.question_americas ,true),
        Question(R.string.question_asia,true))
    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate(Bundle?) called")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var trueButton:Button = findViewById(R.id.true_button)
        var falseButton:Button = findViewById(R.id.false_button)
        var nextButton:Button = findViewById(R.id.next_button)
        var prevButton:Button = findViewById(R.id.prev_button)

        questionTextView = findViewById(R.id.question_text_view)

        trueButton.setOnClickListener { view: View ->
            /*Toast(this).setGravity(Gravity.TOP,0,0)
            Toast(this).show()*///хочу посмотреть как будет смещаться тост
            checkAnswer(true)
        }
        falseButton.setOnClickListener { view: View ->
            checkAnswer(false)
        }
        nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }
        prevButton.setOnClickListener {
            if (currentIndex == 0) currentIndex = 5
            currentIndex = (currentIndex - 1) % questionBank.size
            updateQuestion()
        }
        questionTextView.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }
            updateQuestion()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called") }
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called") }
    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called") }
    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called") }
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called") }

    private fun updateQuestion() {
        val questionTextResId = questionBank[currentIndex].textResId
        questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer
        val messageResId = if (userAnswer != correctAnswer) { R.string.incorrect_toast } else { R.string.correct_toast }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT) .show()
    }
}