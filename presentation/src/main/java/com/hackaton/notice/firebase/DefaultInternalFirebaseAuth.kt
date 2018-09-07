package com.hackaton.notice.firebase

import com.google.firebase.auth.FirebaseAuth
import com.hackaton.domain.di.SchedulerProvider
import com.hackaton.notice.util.rx.with
import io.reactivex.Single

class DefaultInternalFirebaseAuth(
        private val schedulerProvider: SchedulerProvider,
        private val internalFirebaseAppRepository: InternalFirebaseApp
) : InternalFirebaseAuth {

    override fun firebaseAuth() = internalFirebaseAppRepository.init().run {
        with(schedulerProvider).flatMap {
            Single.just(FirebaseAuth(it))
        }
    }
}