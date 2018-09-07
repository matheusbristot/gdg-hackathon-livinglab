package com.hackaton.notice

import android.support.multidex.MultiDexApplication
import com.google.firebase.analytics.FirebaseAnalytics
import com.hackaton.notice.di.fuckYouAppModules
import org.koin.android.ext.android.startKoin

class FuckYouApplication: MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, fuckYouAppModules)
        startAnalytics()
    }

    private fun startAnalytics() {
        if(!BuildConfig.DEBUG) {
            FirebaseAnalytics.getInstance(this)
        }
    }
}