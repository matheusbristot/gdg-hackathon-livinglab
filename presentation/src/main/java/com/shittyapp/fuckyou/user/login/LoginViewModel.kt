package com.shittyapp.fuckyou.user.login

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.OnLifecycleEvent
import com.shittyapp.domain.di.SchedulerProvider
import com.shittyapp.fuckyou.base.view.BaseViewModel

class LoginViewModel(
        private val schedulerProvider: SchedulerProvider
) : BaseViewModel() {

    private val nameLiveData: MutableLiveData<String> = MutableLiveData()
    val name: LiveData<String> get() = nameLiveData

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        nameLiveData.value = "Hello, World!"
    }

}