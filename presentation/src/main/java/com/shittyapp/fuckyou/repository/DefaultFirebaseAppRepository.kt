package com.shittyapp.fuckyou.repository

import android.content.Context
import com.google.firebase.FirebaseApp
import io.reactivex.Single

class DefaultFirebaseAppRepository(context: Context): FirebaseAppRepository {
    override fun init(): Single<FirebaseApp> {
        return Single.just(FirebaseApp.initializeApp(context))
    }

    private val context = context.applicationContext

}