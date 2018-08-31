package com.shittyapp.fuckyou.repository

import com.google.firebase.FirebaseApp
import io.reactivex.Single

interface FirebaseAppRepository {
    fun init(): Single<FirebaseApp>
}