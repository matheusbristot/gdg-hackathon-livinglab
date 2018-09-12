package com.hackaton.notice.view.quiz

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.OnLifecycleEvent
import com.crashlytics.android.Crashlytics
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.hackaton.data.boundaries.FirebaseReference
import com.hackaton.domain.di.SchedulerProvider
import com.hackaton.domain.entities.Quiz
import com.hackaton.domain.interactors.CalculateQuiz
import com.hackaton.notice.base.view.BaseViewModel
import com.hackaton.notice.util.rx.with

class QuizViewModel(
        private val schedulerProvider: SchedulerProvider,
        private val databaseReference: FirebaseReference,
        private val calculateQuiz: CalculateQuiz
) : BaseViewModel() {

    val quizList: LiveData<List<Quiz>> get() = quizListLiveData
    val result: LiveData<String> get() = resultLiveData

    private val quizListLiveData: MutableLiveData<List<Quiz>> = MutableLiveData()
    private val resultLiveData: MutableLiveData<String> = MutableLiveData()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun getQuizList() {
        launch {
            databaseReference.getQuizzReference().with(schedulerProvider).subscribe({
                it.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        val mutableList = mutableListOf<Quiz>()
                        dataSnapshot.children.forEach { mutableList.add(Quiz(it.child("id").value.toString(), it.child("description").value.toString())) }
                        quizListLiveData.value = mutableList.toList()
                    }

                    override fun onCancelled(dataBaseError: DatabaseError) {
                        Crashlytics.logException(dataBaseError.toException().cause)
                    }
                })
            }, { Crashlytics.logException(it) })
        }
    }

    fun calculate(list: List<Int>) {
        resultLiveData.value = calculateQuiz.execute(list).blockingGet()
    }
}