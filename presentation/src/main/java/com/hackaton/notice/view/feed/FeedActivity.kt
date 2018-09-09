package com.hackaton.notice.view.feed

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.hackaton.domain.entities.Politic
import com.hackaton.notice.R
import com.hackaton.notice.databinding.ActivityFeedBinding
import com.hackaton.notice.util.observe
import org.koin.android.architecture.ext.viewModel

class FeedActivity: AppCompatActivity() {

    private lateinit var binding: ActivityFeedBinding
    private lateinit var adapter: FeedAdapter

    private val feedViewModel: FeedViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_feed)
        lifecycle.addObserver(feedViewModel)
        subscribeUi()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        adapter = FeedAdapter()
        binding.politiciansRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.politiciansRecyclerView.adapter = adapter
    }

    private fun subscribeUi() {
        feedViewModel.politicians.observe(this, ::onPoliticiansReceived)
    }

    private fun onPoliticiansReceived(politicians: MutableList<Politic>?) {
        politicians?.let { adapter.setPoliticians(it) }
    }
}