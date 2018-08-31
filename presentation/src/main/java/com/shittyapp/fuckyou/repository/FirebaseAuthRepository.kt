package com.shittyapp.fuckyou.repository

import com.google.firebase.auth.FirebaseAuth
import io.reactivex.Single

interface FirebaseAuthRepository {
    fun firebaseAuth(): Single<FirebaseAuth>
}