package com.hackaton.data.repository

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.hackaton.data.boundaries.FirebaseReference
import io.reactivex.Single

class DefaultFirebaseReference : FirebaseReference {

    override fun getReference(): Single<DatabaseReference> {
        return Single.just(FirebaseDatabase.getInstance().getReference("users"))
    }
}