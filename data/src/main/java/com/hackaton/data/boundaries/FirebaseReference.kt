package com.hackaton.data.boundaries

import com.google.firebase.database.DatabaseReference
import io.reactivex.Single

interface FirebaseReference {

    fun getUserReference(): Single<DatabaseReference>

    fun getQuizzReference(): Single<DatabaseReference>
}