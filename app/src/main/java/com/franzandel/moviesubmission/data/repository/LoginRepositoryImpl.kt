package com.franzandel.moviesubmission.data.repository

import com.franzandel.moviesubmission.core.data.wrapper.Result
import com.franzandel.moviesubmission.core.data.wrapper.data
import com.franzandel.moviesubmission.core.data.wrapper.suspendTryCatch
import com.franzandel.moviesubmission.data.local.LoginLocalData
import com.franzandel.moviesubmission.data.remote.LoginRemoteData
import com.franzandel.moviesubmission.domain.model.LoginReq
import com.franzandel.moviesubmission.domain.repository.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val remoteData: LoginRemoteData,
    private val localData: LoginLocalData
) : LoginRepository {

    override suspend fun login(loginReq: LoginReq): Result<Unit> =
        suspendTryCatch {
            val username = remoteData.login(loginReq).data
            localData.storeUsername(username)
        }

    override suspend fun getUsername(): Result<String> =
        localData.getUsername()
}