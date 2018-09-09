package com.hackaton.notice.view.feed

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.hackaton.domain.entities.Politic
import com.hackaton.notice.databinding.ItemFeedBinding

class FeedViewHolder(private val view: ItemFeedBinding): RecyclerView.ViewHolder(view.root) {
    companion object {
        fun inflate(parent: ViewGroup?) = FeedViewHolder(ItemFeedBinding.inflate(LayoutInflater.from(parent?.context), parent, false))
    }

    fun format(politic: Politic) {

    }
}