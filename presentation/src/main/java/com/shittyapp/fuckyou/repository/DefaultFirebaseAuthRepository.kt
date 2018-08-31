package com.shittyapp.fuckyou.repository

import com.google.firebase.auth.FirebaseAuth
import com.shittyapp.domain.di.SchedulerProvider
import com.shittyapp.fuckyou.util.rx.with
import io.reactivex.Single

class DefaultFirebaseAuthRepository(
        private val schedulerProvider: SchedulerProvider,
        private val firebaseAppRepository: FirebaseAppRepository
) : FirebaseAuthRepository {
    override fun firebaseAuth(): Single<FirebaseAuth> {
        return firebaseAppRepository.init().run {
            with(schedulerProvider).flatMap {
                Single.just(FirebaseAuth(it))
            }
        }
    }
}