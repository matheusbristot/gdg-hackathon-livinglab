package com.hackaton.notice.view.feed

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.OnLifecycleEvent
import com.crashlytics.android.Crashlytics
import com.hackaton.data.boundaries.PoliticiansRepository
import com.hackaton.domain.di.SchedulerProvider
import com.hackaton.domain.entities.Politic
import com.hackaton.notice.base.view.BaseViewModel
import com.hackaton.notice.util.rx.with

class FeedViewModel(
        private val politiciansRepository: PoliticiansRepository,
        private val schedulerProvider: SchedulerProvider
) : BaseViewModel() {
    val politicians: LiveData<MutableList<Politic>> get() = politiciansLiveData

    private val politiciansLiveData: MutableLiveData<MutableList<Politic>> = MutableLiveData()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun getPoliticians() {
        politiciansRepository.getPoliticians()
                .with(schedulerProvider)
                .subscribe({
                    politiciansLiveData.value = it.politics.toMutableList()
                }, {
                    Crashlytics.log(it.message)
                })
    }
}