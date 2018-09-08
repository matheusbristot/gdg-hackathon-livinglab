package com.hackaton.notice.view.dashboard

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.OnLifecycleEvent
import android.util.Log
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.hackaton.data.boundaries.FirebaseReference
import com.hackaton.data.boundaries.PostRepository
import com.hackaton.domain.di.SchedulerProvider
import com.hackaton.domain.entities.Preference
import com.hackaton.notice.base.view.BaseViewModel
import com.hackaton.notice.util.FlexibleLiveData
import com.hackaton.notice.util.rx.with

class MainViewModel(
        private val firebaseUser: FirebaseUser,
        private val schedulerProvider: SchedulerProvider,
        private val postRepository: PostRepository,
        private val databaseReference: FirebaseReference
) : BaseViewModel() {

    val preferences: LiveData<List<Preference>> get() = preferencesLiveData
    private val preferencesLiveData: FlexibleLiveData<List<Preference>> = FlexibleLiveData()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        preferencesLiveData.value = listOf(
                Preference(1, "Aqui vai um pinto"),
                Preference(2, "Cu do gustavo"),
                Preference(3, "Cu no paulinho na reta")
        )

        launch {
            databaseReference.getReference().with(schedulerProvider).subscribe({
                val hashMap = hashMapOf<String, String>()
                hashMap["0"] = "1"
                hashMap["1"] = "2"
                hashMap["2"] = "3"
                hashMap["3"] = "4"
                hashMap["4"] = "5"
                it.child(firebaseUser.uid).child("preferences").setValue(hashMap)

                /*.addValueEventListener(object : ValueEventListener{
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        Log.e("onDataChange", ""+dataSnapshot.value)
                    }

                    override fun onCancelled(p0: DatabaseError) {
                    }
                })*/

            }, {

            })
        }
    }
}