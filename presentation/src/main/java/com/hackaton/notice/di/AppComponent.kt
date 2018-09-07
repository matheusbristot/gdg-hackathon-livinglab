package com.hackaton.notice.di

import android.content.Context
import com.hackaton.data.di.RepositoryComponents
import com.hackaton.domain.di.SchedulerProvider
import com.hackaton.notice.firebase.DefaultFirebaseApp
import com.hackaton.notice.firebase.DefaultInternalFirebaseAuth
import com.hackaton.notice.firebase.InternalFirebaseApp
import com.hackaton.notice.firebase.InternalFirebaseAuth
import com.hackaton.notice.view.dashboard.MainViewModel
import com.hackaton.notice.util.rx.DefaultSchedulerProvider
import org.koin.android.architecture.ext.viewModel
import org.koin.dsl.module.applicationContext

val rxModule = applicationContext {
    bean { DefaultSchedulerProvider() as SchedulerProvider }
}

val viewModule = applicationContext {
    viewModel { MainViewModel(get(), get()) }
//    bean { SigInValidation() }
}

val firebaseDependencies = applicationContext {
    bean { DefaultFirebaseApp(get() as Context) as InternalFirebaseApp }
    bean { DefaultInternalFirebaseAuth(get(), get()) as InternalFirebaseAuth }
}

val fuckYouAppModules = listOf(rxModule, viewModule, firebaseDependencies) + RepositoryComponents.execute()