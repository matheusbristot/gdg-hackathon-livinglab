package com.shittyapp.fuckyou.di

import com.shittyapp.data.util.rx.DefaultSchedulerProvider
import com.shittyapp.domain.di.SchedulerProvider
import com.shittyapp.fuckyou.user.login.LoginViewModel
import org.koin.android.architecture.ext.viewModel
import org.koin.dsl.module.applicationContext

val rxModule = applicationContext {
    bean { DefaultSchedulerProvider() as SchedulerProvider }
}

val viewModule = applicationContext {
    viewModel {
        LoginViewModel(get())
    }
}

val fuckYouAppModules = listOf(rxModule, viewModule)