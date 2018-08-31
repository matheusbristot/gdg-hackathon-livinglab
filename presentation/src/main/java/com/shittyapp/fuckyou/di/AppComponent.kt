package com.shittyapp.fuckyou.di

import android.content.Context
import com.shittyapp.fuckyou.util.rx.DefaultSchedulerProvider
import com.shittyapp.domain.di.SchedulerProvider
import com.shittyapp.fuckyou.repository.DefaultFirebaseAppRepository
import com.shittyapp.fuckyou.repository.DefaultFirebaseAuthRepository
import com.shittyapp.fuckyou.repository.FirebaseAppRepository
import com.shittyapp.fuckyou.repository.FirebaseAuthRepository
import com.shittyapp.fuckyou.user.login.LoginViewModel
import org.koin.android.architecture.ext.viewModel
import org.koin.dsl.module.applicationContext

val rxModule = applicationContext {
    bean { DefaultSchedulerProvider() as SchedulerProvider }
}

val viewModule = applicationContext {
    viewModel {
        LoginViewModel(get(), get())
    }
}

val firebaseDependencies = applicationContext {
    bean {
        DefaultFirebaseAppRepository(get() as Context) as FirebaseAppRepository
    }

    bean {
        DefaultFirebaseAuthRepository(get(), get()) as FirebaseAuthRepository
    }
}

val fuckYouAppModules = listOf(rxModule, viewModule, firebaseDependencies)