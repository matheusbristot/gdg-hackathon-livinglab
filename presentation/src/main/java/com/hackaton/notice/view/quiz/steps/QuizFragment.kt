package com.hackaton.notice.view.quiz.steps

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import com.hackaton.notice.R
import com.hackaton.notice.databinding.FragmentQuizBinding
import com.hackaton.notice.util.view.load
import com.hackaton.notice.view.quiz.QuizActivity

class QuizFragment : Fragment() {

    private lateinit var binding: FragmentQuizBinding

    companion object {
        const val EXTRA_DESCRIPTION = "EXTRA_DESCRIPTION"
        const val EXTRA_POSITION = "EXTRA_POSITION"

        fun getInstance(position: Int, description: String): QuizFragment {
            val fragment = QuizFragment()
            val bundle = Bundle()
            bundle.putString(EXTRA_DESCRIPTION, description)
            bundle.putInt(EXTRA_POSITION, position)
            fragment.arguments = bundle
            return fragment
        }
    }

    private var candidate = 0
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_quiz, container, false)
        initBackground()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupClickListeners()
        setupView()
    }

    private fun setupView() {
        binding.textView2.text = arguments?.getString(EXTRA_DESCRIPTION)
        binding.containerRadioGroup.setOnCheckedChangeListener { button, _ ->
            binding.next.isEnabled = true

            val nameButtonSelected = button.checkedRadioButtonId
            val a = view?.findViewById<RadioButton>(nameButtonSelected)
            when (a?.text) {
                "Discordo" -> candidate = 10
                "Neutro" -> candidate = 0
                "Concordo" -> candidate = 20
            }
        }
    }

    private fun setupClickListeners() {
        binding.next.setOnClickListener { (activity as QuizActivity).goToNext(candidate) }
        binding.previous.setOnClickListener { (activity as QuizActivity).goToPrevious() }
    }

    private fun initBackground() {
        when (arguments?.getInt(EXTRA_POSITION)) {
            0 -> binding.imageView.load(R.drawable.s1, R.drawable.s1)
            1 -> binding.imageView.load(R.drawable.s2, R.drawable.s2)
            2 -> binding.imageView.load(R.drawable.s3, R.drawable.s3)
            3 -> binding.imageView.load(R.drawable.s4, R.drawable.s4)
            4 -> binding.imageView.load(R.drawable.s5, R.drawable.s5)
            5 -> binding.imageView.load(R.drawable.s6, R.drawable.s6)
            6 -> binding.imageView.load(R.drawable.s7, R.drawable.s7)
            7 -> binding.imageView.load(R.drawable.s8, R.drawable.s8)
            8 -> binding.imageView.load(R.drawable.s9, R.drawable.s9)
            9 -> binding.imageView.load(R.drawable.s10, R.drawable.s10)
        }
    }
}