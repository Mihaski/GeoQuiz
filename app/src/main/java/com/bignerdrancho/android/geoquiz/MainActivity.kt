package com.bignerdrancho.android.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
// import android.view.Gravity
//import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button
    private lateinit var prevButton: Button
    private lateinit var questionTextView:TextView
    private val questionBank = listOf(
        Question(R.string.question_australia,true,false),
        Question(R.string.question_oceans,true, false),
        Question(R.string.question_mideast,false, false),
        Question(R.string.question_africa,false, false),
        Question(R.string.question_americas ,true, false),
        Question(R.string.question_asia,true, false))
    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate(Bundle?) called")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
            if (currentIndex == 6) currentIndex = 0
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

        if (questionBank[currentIndex].choice == false) {
            trueButton.isEnabled = true
            falseButton.isEnabled = true }
        else {
            trueButton.isEnabled = false
            falseButton.isEnabled = false}

        val questionTextResId = questionBank[currentIndex].textResId
        questionTextView.setText(questionTextResId)

    }

    private var percentageCorrect = 0

    private fun checkAnswer(userAnswer: Boolean) {
        trueButton.isEnabled = false
        falseButton.isEnabled = false
        questionBank[currentIndex].choice = true
        val correctAnswer = questionBank[currentIndex].answer
        if (userAnswer != correctAnswer) {percentageCorrect+=0} else {percentageCorrect+=1}
        val messageResId = if (userAnswer != correctAnswer) { getString(R.string.incorrect_toast) } else { getString(R.string.correct_toast) }
        Toast.makeText(this, "$messageResId $percentageCorrect/6", Toast.LENGTH_SHORT ) .show()
        //Toast.makeText(this, percentageCorrect.toString()+"/6" , Toast.LENGTH_SHORT ) .show()
    }
}