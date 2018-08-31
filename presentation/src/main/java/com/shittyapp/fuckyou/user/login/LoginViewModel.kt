package com.shittyapp.fuckyou.user.login

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import com.shittyapp.domain.di.SchedulerProvider
import com.shittyapp.fuckyou.base.view.BaseViewModel
import com.shittyapp.fuckyou.repository.FirebaseAuthRepository
import com.shittyapp.fuckyou.util.rx.with

class LoginViewModel(
        private val schedulerProvider: SchedulerProvider,
        private val firebaseAuthRepository: FirebaseAuthRepository
) : BaseViewModel() {

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        firebaseAuthRepository.firebaseAuth()
                .with(schedulerProvider)
                .subscribe({
                    it.signInWithEmailAndPassword("guguhra2013@gmail.com", "caralhoVaoSeFuderCAralhofljnweklJFBLWEKJHFGHRBGHKLERSBGKLERB").addOnCompleteListener {
                        it.isSuccessful
                    }
                }, {

                })
    }

}