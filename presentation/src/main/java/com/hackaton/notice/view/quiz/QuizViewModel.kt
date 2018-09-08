package com.hackaton.notice.view.quiz

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.OnLifecycleEvent
import android.util.Log
import com.crashlytics.android.Crashlytics
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.hackaton.data.boundaries.FirebaseReference
import com.hackaton.domain.di.SchedulerProvider
import com.hackaton.domain.entities.Quiz
import com.hackaton.notice.base.view.BaseViewModel
import com.hackaton.notice.util.rx.with

class QuizViewModel(
        private val schedulerProvider: SchedulerProvider,
        private val databaseReference: FirebaseReference
) : BaseViewModel() {

    val quisList: LiveData<List<Quiz>> get() = quizListLiveData
    val answers: LiveData<MutableList<Int>> get() = answersLiveData

    private val quizListLiveData: MutableLiveData<List<Quiz>> = MutableLiveData()
    private val answersLiveData: MutableLiveData<MutableList<Int>> = MutableLiveData()

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
        var PF = 0
        var PE = 0
        list.forEachIndexed { index, i ->
            when {
                index <= 4 -> PF += i
                index in 5..9 -> PE += i
            }
        }
        Log.e("RESULTADOS", "PF: $PF <::> PE: $PE")
        if (PF <= 50 && PE <= 50) Log.e("RESULTADOS", "bolsonomindsaijsd")
    }
}