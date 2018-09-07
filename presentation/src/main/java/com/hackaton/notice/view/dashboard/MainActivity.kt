package com.hackaton.notice.view.dashboard

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import com.hackaton.domain.entities.Preference
import com.hackaton.notice.R
import com.hackaton.notice.databinding.ActivityDashboardBinding
import com.hackaton.notice.util.observe
import org.koin.android.architecture.ext.viewModel

class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var binding: ActivityDashboardBinding
    private lateinit var adapter: PreferencesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard)
        setupRecyclerView()
        lifecycle.addObserver(mainViewModel)
        observerUi()
    }

    private fun observerUi() {
        mainViewModel.preferences.observe(this, ::onGetPreferenceList)
    }

    private fun setupRecyclerView() {
        adapter = PreferencesAdapter()
        binding.preferencesRecyclerView.adapter = adapter
        val gridLayoutManger = GridLayoutManager(this, 3)
        binding.preferencesRecyclerView.layoutManager = gridLayoutManger
    }

    private fun onGetPreferenceList(preferences: List<Preference>?) {
        preferences?.let {
            adapter.setPreferences(it)
        }
    }

}