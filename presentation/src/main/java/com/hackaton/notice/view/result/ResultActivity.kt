package com.hackaton.notice.view.result

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.hackaton.notice.R
import com.hackaton.notice.databinding.ActivityResultBinding
import com.hackaton.notice.util.RESULT
import com.hackaton.notice.util.observe
import com.hackaton.notice.util.view.Arguments
import com.hackaton.notice.util.view.argument
import com.hackaton.notice.util.view.load
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask
import org.koin.android.architecture.ext.viewModel
import org.koin.standalone.KoinComponent

class ResultActivity : AppCompatActivity(), KoinComponent {

    private val result: String by argument(Arguments.ARG_RESULT)
    private val viewModel: ResultViewModel by viewModel { mapOf(RESULT to result) }
    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_result)
        binding.goToList.setOnClickListener(::goTo)
        lifecycle.addObserver(viewModel)
        observerUi()
    }

    private fun observerUi() {
        viewModel.background.observe(this, ::setBackground)
        viewModel.title.observe(this, ::onSetTitle)
        viewModel.body.observe(this, ::onSetBody)
    }

    private fun setBackground(resource: Int?) {
        resource?.let {
            binding.resultImageView.load(resource, resource)
        }
    }

    private fun goTo(view: View) {
//        startActivity(intentFor<SUA_ACTIVITY_ROSS>().newTask().clearTask())
    }

    private fun onSetTitle(title: Int?) {
        title?.let {
            binding.resultTitleTextView.text = getString(it)
        }
    }

    private fun onSetBody(body: Int?) {
        body?.let {
            binding.resultBodyTextView.text = getString(it)
        }
    }
}