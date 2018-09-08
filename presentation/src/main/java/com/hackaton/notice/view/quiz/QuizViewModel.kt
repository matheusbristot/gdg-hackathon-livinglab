package com.hackaton.notice.view.quiz

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.OnLifecycleEvent
import com.hackaton.domain.entities.Quiz
import com.hackaton.notice.base.view.BaseViewModel

class QuizViewModel(

) : BaseViewModel() {

    val quizList: List<Quiz> = listOf(
            Quiz(1, "Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."),
            Quiz(2, "It is a long established fact that a reader will be distracted by the readable content of a page"),
            Quiz(3, "Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).")
    )
    private val quizListLiveData: MutableLiveData<List<Quiz>> = MutableLiveData()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun getQuizList() {
        quizListLiveData.value = quizList
    }
}