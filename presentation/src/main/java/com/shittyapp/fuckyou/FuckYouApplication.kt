package com.shittyapp.fuckyou

import android.support.multidex.MultiDexApplication
import com.shittyapp.fuckyou.di.fuckYouAppModules
import org.koin.android.ext.android.startKoin

class FuckYouApplication: MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, fuckYouAppModules)
    }
}