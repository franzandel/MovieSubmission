package com.franzandel.moviesubmission.data.remote

import com.franzandel.moviesubmission.core.data.wrapper.Result
import com.franzandel.moviesubmission.domain.model.LoginReq

/**
 * Created by Franz Andel on 24/08/21.
 * Android Engineer
 */

interface LoginRemoteData {
    suspend fun login(loginReq: LoginReq): Result<String>
}