package com.hackaton.notice.di

import android.content.Context
import com.hackaton.data.di.RepositoryComponents
import com.hackaton.domain.di.SchedulerProvider
import com.hackaton.notice.firebase.*
import com.hackaton.notice.util.FIREBASE_USER
import com.hackaton.notice.util.rx.DefaultSchedulerProvider
import com.hackaton.notice.view.dashboard.MainViewModel
import com.hackaton.notice.view.login.LoginViewModel
import com.hackaton.notice.view.quiz.QuizViewModel
import com.hackaton.notice.view.splash.SplashScreenViewModel
import org.koin.android.architecture.ext.viewModel
import org.koin.dsl.module.applicationContext

val rxModule = applicationContext {
    bean { DefaultSchedulerProvider() as SchedulerProvider }
}

val viewModule = applicationContext {
    viewModel { params -> MainViewModel(params[FIREBASE_USER], get(), get()) }
    viewModel { SplashScreenViewModel(get()) }
    viewModel { LoginViewModel(get(), get(), get(), get()) }
    viewModel { QuizViewModel(get(), get()) }
}

val firebaseDependencies = applicationContext {
    bean { DefaultFirebaseApp(get() as Context) as InternalFirebaseApp }
    bean { DefaultInternalFirebaseAuth(get(), get()) as InternalFirebaseAuth }
    bean { GetCurrentUser(get(), get()) }
    bean { DefaultFirebaseErrorHandler() as FirebaseErrorHandler }
}

val fuckYouAppModules = listOf(rxModule, viewModule, firebaseDependencies) + RepositoryComponents.execute()