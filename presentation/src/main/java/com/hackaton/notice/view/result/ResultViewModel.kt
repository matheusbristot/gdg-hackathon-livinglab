package com.hackaton.notice.view.result

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.OnLifecycleEvent
import com.hackaton.domain.DIREITA
import com.hackaton.domain.ESQUERDA
import com.hackaton.domain.LIBERTARIO
import com.hackaton.domain.SOCIALISTA
import com.hackaton.notice.R
import com.hackaton.notice.base.view.BaseViewModel

class ResultViewModel(private val result: String) : BaseViewModel() {

    val background: LiveData<Int> get() = backgroundLiveData
    val title: LiveData<Int> get() = titleLiveData
    val body: LiveData<Int> get() = bodyLiveData

    private val backgroundLiveData: MutableLiveData<Int> = MutableLiveData()
    private val titleLiveData: MutableLiveData<Int> = MutableLiveData()
    private val bodyLiveData: MutableLiveData<Int> = MutableLiveData()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        resolveBackground()
    }

    private fun resolveBackground() {
        when (result) {
            SOCIALISTA -> {
                backgroundLiveData.value = R.drawable.esquerda_conservaora
                titleLiveData.value = R.string.title_left_traditional
                bodyLiveData.value = R.string.text_left_traditional
            }
            DIREITA -> {
                backgroundLiveData.value = R.drawable.direita_conservadora
                titleLiveData.value = R.string.title_right_conserv
                bodyLiveData.value = R.string.text_right_conserv
            }
            ESQUERDA -> {
                backgroundLiveData.value = R.drawable.esquerda_liberal
                titleLiveData.value = R.string.title_left_libert
                bodyLiveData.value = R.string.text_left_libert
            }
            LIBERTARIO -> {
                backgroundLiveData.value = R.drawable.direita_liberal
                titleLiveData.value = R.string.title_right_libert
                bodyLiveData.value = R.string.text_right_libert
            }
            else -> {
                backgroundLiveData.value = R.drawable.centro
                titleLiveData.value = R.string.title_center
                bodyLiveData.value = R.string.text_center
            }
        }
    }
}