package com.shittyapp.fuckyou.user.login

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import com.shittyapp.data.boundaries.PostRepository
import com.shittyapp.domain.di.SchedulerProvider
import com.shittyapp.fuckyou.base.view.BaseViewModel
import com.shittyapp.fuckyou.firebase.InternalFirebaseAuth
import com.shittyapp.fuckyou.util.rx.with

class LoginViewModel(
        private val schedulerProvider: SchedulerProvider,
        private val firebaseAuthRepository: InternalFirebaseAuth,
        private val postRepository: PostRepository
) : BaseViewModel() {

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
//        firebaseAuthRepository.firebaseAuth()
//                .with(schedulerProvider)
//                .subscribe({
//                    it.signInWithEmailAndPassword("guguhra2013@gmail.com", "caralhoVaoSeFuderCAralhofljnweklJFBLWEKJHFGHRBGHKLERSBGKLERB").addOnCompleteListener {
//                        it.isSuccessful
//                    }
//                }, {
//
//                })

        postRepository.getPosts(1).with(schedulerProvider)
                .subscribe({
                    it
                }, {
                    // nothing
                })
    }

}