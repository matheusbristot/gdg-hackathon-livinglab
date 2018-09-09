package com.hackaton.notice.view.dashboard

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.hackaton.domain.entities.PreferenceQuiz

class PreferencesAdapter(private val list: List<PreferenceQuiz>) : RecyclerView.Adapter<PreferenceViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) = PreferenceViewHolder.inflate(viewGroup)

    override fun getItemCount() = list.size

    override fun onBindViewHolder(preferenceViewHolder: PreferenceViewHolder, position: Int) = preferenceViewHolder.setPreference(list[position])

}