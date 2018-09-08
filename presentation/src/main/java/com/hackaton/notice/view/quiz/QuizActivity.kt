package com.hackaton.notice.view.quiz

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.hackaton.notice.databinding.ActivityQuizBinding
import com.hackaton.notice.view.quiz.steps.QuizAdapter
import org.koin.android.architecture.ext.viewModel

class QuizActivity: AppCompatActivity() {
    private lateinit var binding: ActivityQuizBinding

    private val quizViewModel: QuizViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewPager()
        subscribeUi()
    }

    private fun subscribeUi() {

    }

    private fun setupViewPager() {
        binding.quizViewPager.adapter = QuizAdapter(supportFragmentManager)
    }
}