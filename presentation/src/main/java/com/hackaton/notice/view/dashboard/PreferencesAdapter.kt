package com.hackaton.notice.view.dashboard

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.hackaton.domain.entities.Preference

class PreferencesAdapter : RecyclerView.Adapter<PreferenceViewHolder>() {

    private val preferenceList: MutableList<Preference> = mutableListOf()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) = PreferenceViewHolder.inflate(viewGroup)

    override fun getItemCount() = preferenceList.size

    override fun onBindViewHolder(
            preferenceViewHolder: PreferenceViewHolder, position: Int
    ) = preferenceViewHolder.setPreference(preferenceList[position])

    fun setPreferences(preferences: List<Preference>) {
        preferenceList.addAll(preferences)
        notifyDataSetChanged()
    }
}