package com.hackaton.notice.view.splash

import android.os.Bundle
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.app.AppCompatActivity
import com.google.firebase.auth.FirebaseUser
import com.hackaton.notice.util.observe
import com.hackaton.notice.util.view.Arguments.ARG_USER
import com.hackaton.notice.view.dashboard.MainActivity
import com.hackaton.notice.view.login.LoginActivity
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask
import org.koin.android.architecture.ext.viewModel

class SplashScreenActivity : AppCompatActivity() {

    private val viewModel: SplashScreenViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(viewModel)
        subscribeUi()
        viewModel.verifyLogged()
    }

    private fun subscribeUi() {
        viewModel.notLogged.observe(this, ::onUserNotLogged)
        viewModel.user.observe(this, ::onUserLogged)
    }

    private fun onUserNotLogged(notLogged: Boolean?) {
        notLogged?.let {
            startActivity(intentFor<LoginActivity>().newTask().clearTask())
        }
    }

    private fun onUserLogged(firebaseUser: FirebaseUser?) {
        firebaseUser?.let {
            startActivity(intentFor<MainActivity>(ARG_USER to it).newTask().clearTask())
        }
    }
}
