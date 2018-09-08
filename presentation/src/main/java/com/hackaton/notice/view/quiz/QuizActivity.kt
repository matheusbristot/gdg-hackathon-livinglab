package com.hackaton.notice.view.quiz

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.hackaton.domain.entities.Quiz
import com.hackaton.notice.R
import com.hackaton.notice.databinding.ActivityQuizBinding
import com.hackaton.notice.util.observe
import com.hackaton.notice.view.quiz.steps.QuizAdapter
import org.koin.android.architecture.ext.viewModel

class QuizActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuizBinding

    private val quizViewModel: QuizViewModel by viewModel()
    private lateinit var adapter: QuizAdapter
    private var list: MutableList<Int> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_quiz)
        setupViewPager()
        lifecycle.addObserver(quizViewModel)
        subscribeUi()
    }

    fun goToNext(value: Int) {
        if (binding.quizViewPager.currentItem < 9) {
            list.add(value)
            binding.quizViewPager.goToNext()
        } else {
            list.add(value)
            quizViewModel.calculate(list)
        }
    }

    fun goToPrevious() {
        binding.quizViewPager.goToPrevious()
    }

    private fun subscribeUi() {
        quizViewModel.quisList.observe(this, ::onGetQuizList)
    }

    private fun onGetQuizList(quizList: List<Quiz>?) {
        quizList?.let {
            adapter.setData(it)
        }
    }

    private fun setupViewPager() {
        adapter = QuizAdapter(supportFragmentManager)
        binding.quizViewPager.adapter = adapter
    }

    fun addAnswer(id: Int) {
        if (binding.quizViewPager.currentItem < list.size) {
            list.add(binding.quizViewPager.currentItem, id)
        } else {
            if (binding.quizViewPager.currentItem == 0) list.add(binding.quizViewPager.currentItem, id)
            else list.add(binding.quizViewPager.currentItem - 1, id)
        }
    }
}