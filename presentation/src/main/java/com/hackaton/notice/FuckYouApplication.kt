package com.hackaton.notice

import android.content.Context
import android.support.multidex.MultiDexApplication
import com.crashlytics.android.Crashlytics
import com.crashlytics.android.core.CrashlyticsCore
import com.google.firebase.analytics.FirebaseAnalytics
import com.hackaton.notice.di.fuckYouAppModules
import io.fabric.sdk.android.Fabric
import org.koin.android.ext.android.startKoin


class FuckYouApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, fuckYouAppModules)
        startAnalytics()
        initFabric(this)
    }

    private fun startAnalytics() {
        if (!BuildConfig.DEBUG) {
            FirebaseAnalytics.getInstance(this)
        }
    }

    private fun initFabric(context: Context) {
        val core = CrashlyticsCore.Builder().disabled(BuildConfig.DEBUG).build()
        val kit = Crashlytics.Builder().core(core).build()
        Fabric.with(context, kit)
    }
}