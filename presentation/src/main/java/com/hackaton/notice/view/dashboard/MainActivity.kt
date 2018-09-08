package com.hackaton.notice.view.dashboard

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import com.google.firebase.auth.FirebaseUser
import com.hackaton.domain.entities.PreferenceQuiz
import com.hackaton.notice.R
import com.hackaton.notice.databinding.ActivityDashboardBinding
import com.hackaton.notice.util.FIREBASE_USER
import com.hackaton.notice.util.observe
import com.hackaton.notice.util.view.Arguments.ARG_USER
import com.hackaton.notice.util.view.argument
import org.koin.android.architecture.ext.viewModel
import org.koin.standalone.KoinComponent

class MainActivity : AppCompatActivity(), KoinComponent {

    private val user: FirebaseUser by argument(ARG_USER)
    private val mainViewModel: MainViewModel by viewModel { mapOf(FIREBASE_USER to user) }
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

    }

    private fun onGetPreferenceList(preferences: List<PreferenceQuiz>?) {
        preferences?.let {
            adapter = PreferencesAdapter(it)
            binding.preferencesRecyclerView.adapter = adapter
            val gridLayoutManger = GridLayoutManager(this, 3)
            binding.preferencesRecyclerView.layoutManager = gridLayoutManger
        }
    }

}