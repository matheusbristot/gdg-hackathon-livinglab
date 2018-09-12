package com.hackaton.notice.view.feed

import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.hackaton.domain.entities.Politic
import com.hackaton.notice.databinding.ItemFeedBinding
import com.hackaton.notice.util.view.load


class FeedViewHolder(private val view: ItemFeedBinding) : RecyclerView.ViewHolder(view.root) {
    companion object {
        fun inflate(parent: ViewGroup?) = FeedViewHolder(ItemFeedBinding.inflate(LayoutInflater.from(parent?.context), parent, false))
    }

    fun format(politic: Politic) {
        politic.picutre?.let {
            if (it.isNotEmpty()) {
                view.imageView2.load(it, true, android.R.drawable.ic_menu_close_clear_cancel)
                view.politicName.text = politic.name
                view.pendencyButton.setOnClickListener { _ ->
                    view.root.context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("http://www.tse.jus.br/")))
                }
                view.ideasButton.setOnClickListener { _ ->
                    view.root.context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.wikipedia.org/")))
                }
            }
        }
}
}