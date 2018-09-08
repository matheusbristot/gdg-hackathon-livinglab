package com.hackaton.data.boundaries

import com.google.firebase.database.DatabaseReference
import io.reactivex.Single

interface FirebaseReference {

    fun getReference(): Single<DatabaseReference>
}