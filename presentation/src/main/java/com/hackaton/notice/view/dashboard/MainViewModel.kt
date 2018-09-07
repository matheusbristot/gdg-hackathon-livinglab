package com.hackaton.notice.view.dashboard

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.OnLifecycleEvent
import com.hackaton.data.boundaries.PostRepository
import com.hackaton.domain.di.SchedulerProvider
import com.hackaton.domain.entities.Preference
import com.hackaton.notice.base.view.BaseViewModel
import com.hackaton.notice.util.FlexibleLiveData

class MainViewModel(
        private val schedulerProvider: SchedulerProvider,
        private val postRepository: PostRepository
) : BaseViewModel() {

    val preferences: LiveData<List<Preference>> get() = preferencesLiveData
    private val preferencesLiveData: FlexibleLiveData<List<Preference>> = FlexibleLiveData()

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        preferencesLiveData.value = listOf(
                Preference(1, "Aqui vai um pinto"),
                Preference(2, "Cu do gustavo"),
                Preference(3, "Cu no paulinho na reta")
        )
    }
}