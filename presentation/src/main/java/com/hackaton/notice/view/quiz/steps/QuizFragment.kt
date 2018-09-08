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
import com.hackaton.notice.view.quiz.QuizActivity

class QuizFragment : Fragment() {

    private lateinit var binding: FragmentQuizBinding

    companion object {
        const val EXTRA_DESCRIPTION = "EXTRA_DESCRIPTION"

        fun getInstance(description: String): QuizFragment {
            val fragment = QuizFragment()
            val bundle = Bundle()
            bundle.putString(EXTRA_DESCRIPTION, description)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_quiz, container, false)
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
            when(a?.text){
                "Discordo" -> (activity as QuizActivity).addAnswer(10)
                "Neutro" -> (activity as QuizActivity).addAnswer(0)
                "Concordo" ->(activity as QuizActivity).addAnswer(20)
            }

        }
    }

    private fun setupClickListeners() {
        binding.next.setOnClickListener { (activity as QuizActivity).goToNext() }
        binding.previous.setOnClickListener { (activity as QuizActivity).goToPrevious() }
    }
}