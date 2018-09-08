package com.hackaton.notice.firebase

import com.google.firebase.auth.FirebaseUser
import com.hackaton.domain.di.SchedulerProvider
import com.hackaton.notice.util.rx.with
import io.reactivex.Single

class GetCurrentUser(
        private val firebaseAuth: InternalFirebaseAuth,
        private val schedulerProvider: SchedulerProvider
) {

    fun isAuthenticated(): Single<Boolean> {
        return firebaseAuth.firebaseAuth().with(schedulerProvider).map { it.currentUser != null }
    }

    fun getUserId(): Single<String?> {
        return firebaseAuth.firebaseAuth().with(schedulerProvider).map { it.currentUser?.uid }
    }

    fun getUser(): Single<FirebaseUser> {
        return firebaseAuth.firebaseAuth().with(schedulerProvider).map { it.currentUser }
    }
}