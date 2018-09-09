package com.hackaton.notice.view.dashboard

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.hackaton.domain.entities.PreferenceQuiz
import com.hackaton.notice.databinding.ItemPreferenceBinding

class PreferenceViewHolder(private val binding: ItemPreferenceBinding) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun inflate(parent: ViewGroup?) = PreferenceViewHolder(ItemPreferenceBinding.inflate(LayoutInflater.from(parent?.context), parent, false))
    }

    fun setPreference(preference: PreferenceQuiz) {
        binding.preferenceDescription.text = preference.description
    }
}