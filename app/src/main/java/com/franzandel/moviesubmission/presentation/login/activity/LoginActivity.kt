package com.franzandel.moviesubmission.presentation.login.activity

import android.widget.Toast
import androidx.activity.viewModels
import com.franzandel.moviesubmission.R
import com.franzandel.moviesubmission.core.external.extension.observe
import com.franzandel.moviesubmission.core.presentation.activity.BaseActivityVM
import com.franzandel.moviesubmission.databinding.ActivityLoginBinding
import com.franzandel.moviesubmission.domain.model.LoginReq
import com.franzandel.moviesubmission.presentation.login.vm.LoginVM
import com.franzandel.moviesubmission.presentation.navigation.MoviesNavigation
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : BaseActivityVM<LoginVM, ActivityLoginBinding>() {

    @Inject
    lateinit var navigation: MoviesNavigation

    private val viewModel: LoginVM by viewModels()

    override fun getVM(): LoginVM = viewModel

    override fun getViewBinding(): ActivityLoginBinding =
        ActivityLoginBinding.inflate(layoutInflater)

    override fun onActivityCreated() {
        setupObservers()
        setupListeners()
        viewModel.getUsername()
    }

    private fun setupObservers() {
        observe(viewModel.login) { login ->
            if (login)
                navigation.goToDashboard()
            else
                Toast.makeText(
                    this,
                    getString(R.string.login_wrong_credentials),
                    Toast.LENGTH_SHORT
                ).show()
        }

        observe(viewModel.username) {
            navigation.goToDashboard()
        }

        observe(viewModel.validateCredentials) { (validateCredentials, loginReq) ->
            if (validateCredentials.isNotEmpty())
                Toast.makeText(this, validateCredentials, Toast.LENGTH_SHORT).show()
            else
                viewModel.login(loginReq)
        }
    }

    private fun setupListeners() {
        binding.btnLogin.setOnClickListener {
            val loginReq = LoginReq(
                username = binding.etUsername.text.toString().trim(),
                password = binding.etPassword.text.toString().trim()
            )
            viewModel.validateCredentials(loginReq)
        }
    }
}