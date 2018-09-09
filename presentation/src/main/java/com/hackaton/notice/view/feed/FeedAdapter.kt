package com.hackaton.notice.view.feed

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.hackaton.domain.entities.Politic

class FeedAdapter(): RecyclerView.Adapter<FeedViewHolder>() {

    private var politicians: MutableList<Politic> = mutableListOf()

    fun setPoliticians(politicians: MutableList<Politic>) {
        this.politicians = politicians
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): FeedViewHolder {
        return FeedViewHolder.inflate(viewGroup)
    }

    override fun getItemCount() = politicians.count()

    override fun onBindViewHolder(viewHolder: FeedViewHolder, position: Int) {
        viewHolder.format(politicians[position])
    }
}