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
import com.hackaton.domain.entities.PreferenceQuizz
import com.hackaton.notice.base.view.BaseViewModel
import com.hackaton.notice.util.rx.with

class QuizViewModel(
        private val schedulerProvider: SchedulerProvider,
        private val databaseReference: FirebaseReference
) : BaseViewModel() {

    val quisList: LiveData<List<PreferenceQuizz>> get() = quizListLiveData

    private val quizListLiveData: MutableLiveData<List<PreferenceQuizz>> = MutableLiveData()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun getPreferenceQuizzList() {
        launch {
            databaseReference.getQuizzReference().with(schedulerProvider).subscribe({
                it.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        val mutableList = mutableListOf<PreferenceQuizz>()
                        dataSnapshot.children.forEach { mutableList.add(PreferenceQuizz(it.child("id").value.toString(), it.child("description").value.toString())) }
                        quizListLiveData.value = mutableList.toList()
                    }

                    override fun onCancelled(dataBaseError: DatabaseError) {
                        Crashlytics.logException(dataBaseError.toException().cause)
                    }
                })
            }, { Crashlytics.logException(it) })
        }
    }
}