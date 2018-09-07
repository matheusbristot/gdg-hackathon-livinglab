package com.shittyapp.fuckyou.user.login

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.shittyapp.fuckyou.R
import com.shittyapp.fuckyou.databinding.ActivityLoginBinding
import com.shittyapp.fuckyou.util.observe
import org.koin.android.architecture.ext.viewModel

class LoginActivity: AppCompatActivity() {
    private val loginViewModel: LoginViewModel by viewModel()
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        lifecycle.addObserver(loginViewModel)
    }
}