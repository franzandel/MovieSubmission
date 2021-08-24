package com.franzandel.moviesubmission.domain.repository

import com.franzandel.moviesubmission.core.data.wrapper.Result
import com.franzandel.moviesubmission.domain.model.LoginReq

/**
 * Created by Franz Andel on 24/08/21.
 * Android Engineer
 */

interface LoginRepository {
    suspend fun login(loginReq: LoginReq): Result<Unit>
    suspend fun getUsername(): Result<String>
}