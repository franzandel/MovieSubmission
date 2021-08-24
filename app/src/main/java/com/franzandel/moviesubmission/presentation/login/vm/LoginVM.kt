package com.franzandel.moviesubmission.presentation.login.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.franzandel.moviesubmission.core.data.wrapper.Result
import com.franzandel.moviesubmission.core.external.coroutine.CoroutineThread
import com.franzandel.moviesubmission.core.presentation.vm.BaseViewModel
import com.franzandel.moviesubmission.domain.model.LoginReq
import com.franzandel.moviesubmission.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Franz Andel on 24/08/21.
 * Android Engineer
 */

@HiltViewModel
class LoginVM @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val coroutineThread: CoroutineThread
) : BaseViewModel() {

    private val _login = MutableLiveData<Boolean>()
    val login: LiveData<Boolean> = _login

    fun login(loginReq: LoginReq) {
        _loading.value = true
        viewModelScope.launch(coroutineThread.background()) {
            when (loginUseCase.login(loginReq)) {
                is Result.Success -> _login.postValue(true)
                is Result.Error -> _login.postValue(false)
            }
            _loading.postValue(false)
        }
    }

    private val _username = MutableLiveData<Unit>()
    val username: LiveData<Unit> = _username

    fun getUsername() {
        _loading.value = true
        viewModelScope.launch(coroutineThread.background()) {
            when (val result = loginUseCase.getUsername()) {
                is Result.Success -> {
                    if (result.data.isNotEmpty())
                        _username.postValue(Unit)
                }
                is Result.Error -> _error.postValue(result.error.localizedMessage)
            }
            _loading.postValue(false)
        }
    }

    private val _validateCredentials = MutableLiveData<Pair<String, LoginReq>>()
    val validateCredentials: LiveData<Pair<String, LoginReq>> = _validateCredentials

    fun validateCredentials(loginReq: LoginReq) {
        when {
            loginReq.username.isEmpty() ->
                _validateCredentials.value = Pair("Username tidak boleh kosong", loginReq)
            loginReq.password.isEmpty() ->
                _validateCredentials.value = Pair("Password tidak boleh kosong", loginReq)
            loginReq.password.length < 4 ->
                _validateCredentials.value = Pair("Password harus lebih dari 4 karakter", loginReq)
            else ->
                _validateCredentials.value = Pair("", loginReq)
        }
    }
}