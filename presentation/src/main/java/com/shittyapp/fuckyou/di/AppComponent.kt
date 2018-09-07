package com.shittyapp.fuckyou.di

import android.content.Context
import com.shittyapp.data.di.RepositoryComponents
import com.shittyapp.domain.di.SchedulerProvider
import com.shittyapp.fuckyou.firebase.DefaultFirebaseApp
import com.shittyapp.fuckyou.firebase.DefaultInternalFirebaseAuth
import com.shittyapp.fuckyou.firebase.InternalFirebaseApp
import com.shittyapp.fuckyou.firebase.InternalFirebaseAuth
import com.shittyapp.fuckyou.user.login.LoginViewModel
import com.shittyapp.fuckyou.util.rx.DefaultSchedulerProvider
import org.koin.android.architecture.ext.viewModel
import org.koin.dsl.module.applicationContext

val rxModule = applicationContext {
    bean { DefaultSchedulerProvider() as SchedulerProvider }
}

val viewModule = applicationContext {
    viewModel {
        LoginViewModel(get(), get(), get())
    }
}

val firebaseDependencies = applicationContext {
    bean {
        DefaultFirebaseApp(get() as Context) as InternalFirebaseApp
    }

    bean {
        DefaultInternalFirebaseAuth(get(), get()) as InternalFirebaseAuth
    }
}

val fuckYouAppModules = listOf(rxModule, viewModule, firebaseDependencies) + RepositoryComponents.execute()