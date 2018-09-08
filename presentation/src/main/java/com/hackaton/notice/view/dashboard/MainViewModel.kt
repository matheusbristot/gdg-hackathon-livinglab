package com.hackaton.notice.view.dashboard

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.OnLifecycleEvent
import com.crashlytics.android.Crashlytics
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.hackaton.data.boundaries.FirebaseReference
import com.hackaton.domain.di.SchedulerProvider
import com.hackaton.domain.entities.PreferenceQuiz
import com.hackaton.notice.base.view.BaseViewModel
import com.hackaton.notice.util.FlexibleLiveData
import com.hackaton.notice.util.rx.with

class MainViewModel(
        private val firebaseUser: FirebaseUser,
        private val schedulerProvider: SchedulerProvider,
        private val databaseReference: FirebaseReference
) : BaseViewModel() {

    val preferences: LiveData<List<PreferenceQuiz>> get() = preferencesLiveData
    private val preferencesLiveData: FlexibleLiveData<List<PreferenceQuiz>> = FlexibleLiveData()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {

    }
}