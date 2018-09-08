package com.hackaton.notice.view.login

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.OnLifecycleEvent
import com.crashlytics.android.Crashlytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.hackaton.domain.di.SchedulerProvider
import com.hackaton.notice.base.view.BaseViewModel
import com.hackaton.notice.firebase.FirebaseErrorHandler
import com.hackaton.notice.firebase.GetCurrentUser
import com.hackaton.notice.firebase.InternalFirebaseAuth
import com.hackaton.notice.util.FlexibleLiveData
import com.hackaton.notice.util.rx.with
import io.reactivex.Observable
import io.reactivex.rxkotlin.Observables
import io.reactivex.subjects.PublishSubject

class LoginViewModel(
        private val schedulerProvider: SchedulerProvider,
        private val instanceFirebaseAuth: InternalFirebaseAuth,
        private val firebaseErrorHandler: FirebaseErrorHandler,
        private val getCurrentUser: GetCurrentUser
) : BaseViewModel() {

    val userLogged: LiveData<FirebaseUser> get() = userLoggedLiveData
    val userSigInFailed: LiveData<String> get() = userSigInFailedLiveData

    var email: PublishSubject<String> = PublishSubject.create()
    var password: PublishSubject<String> = PublishSubject.create()
    val isSignInEnabled: Observable<Boolean> = Observables.combineLatest(email, password) { u, p ->
        u.isNotEmpty() && p.isNotEmpty()
    }

    private var inputEmail = ""
    private var inputPassword = ""
    private val userLoggedLiveData: FlexibleLiveData<FirebaseUser> = FlexibleLiveData()
    private val userSigInFailedLiveData: FlexibleLiveData<String> = FlexibleLiveData()
    private var firebaseAuth: FirebaseAuth? = null

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun getDetail() {
        launch {
            instanceFirebaseAuth.firebaseAuth()
                    .with(schedulerProvider)
                    .subscribe(::onSuccessFirebaseAuth, ::onFailureFirebaseAuth)
        }
    }

    fun onGetEmailText(email: String) {
        inputEmail = email
        this.email.onNext(email)
    }

    fun onGetPasswordText(password: String) {
        inputPassword = password
        this.password.onNext(password)
    }

    private fun onSuccessFirebaseAuth(firebaseAuth: FirebaseAuth?) {
        firebaseAuth?.let { this.firebaseAuth = it }
    }

    private fun onFailureFirebaseAuth(throwable: Throwable) {
        Crashlytics.logException(throwable)
    }

    fun signIn() {
        firebaseAuth?.run {
            signInWithEmailAndPassword(inputEmail, inputPassword).addOnCompleteListener {
                if (it.isSuccessful) {
                    userLoggedLiveData.value = currentUser
                } else {
                    it.exception?.let {
                        userSigInFailedLiveData.value = firebaseErrorHandler.getMessageError(it)
                    }
                }
            }
        }
    }
}