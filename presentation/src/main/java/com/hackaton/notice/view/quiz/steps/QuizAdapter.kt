package com.hackaton.notice.view.quiz.steps

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.hackaton.domain.entities.Quiz

class QuizAdapter(fragmentManger: FragmentManager): FragmentPagerAdapter(fragmentManger) {

    private val quizList: MutableList<Quiz> = mutableListOf()

    override fun getItem(position: Int): Fragment {
        return QuizFragment.getInstance(quizList[position].description)
    }

    override fun getCount(): Int  = quizList.count()

}