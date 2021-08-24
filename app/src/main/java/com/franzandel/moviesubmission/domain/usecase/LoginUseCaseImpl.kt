package com.franzandel.moviesubmission.domain.usecase

import com.franzandel.moviesubmission.core.data.wrapper.Result
import com.franzandel.moviesubmission.domain.model.LoginReq
import com.franzandel.moviesubmission.domain.repository.LoginRepository
import javax.inject.Inject

class LoginUseCaseImpl @Inject constructor(private val repository: LoginRepository) : LoginUseCase {

    override suspend fun login(loginReq: LoginReq): Result<Unit> =
        repository.login(loginReq)

    override suspend fun getUsername(): Result<String> =
        repository.getUsername()
}