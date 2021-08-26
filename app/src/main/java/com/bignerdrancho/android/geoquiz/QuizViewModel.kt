package com.bignerdrancho.android.geoquiz

import androidx.lifecycle.ViewModel



class QuizViewModel:ViewModel() {

    private val questionBank = listOf(
        Question(R.string.question_australia,true,false),
        Question(R.string.question_oceans,true, false),
        Question(R.string.question_mideast,false, false),
        Question(R.string.question_africa,false, false),
        Question(R.string.question_americas ,true, false),
        Question(R.string.question_asia,true, false))

    var currentIndex = 0

        val currentQuestionAnswer: Boolean
            get() = questionBank[currentIndex].answer
        val currentQuestionText: Int
            get() = questionBank[currentIndex].textResId
        var choice : Boolean
            get() = questionBank[currentIndex].choice
            set(value) { questionBank[currentIndex].choice = value }

    fun moveToNext() {
        if (currentIndex == 6) currentIndex = 0
        currentIndex = (currentIndex + 1) % questionBank.size
    }
    fun moveToPrev() {
        if (currentIndex == 0) currentIndex = 6
        currentIndex = (currentIndex - 1) % questionBank.size
    }
}