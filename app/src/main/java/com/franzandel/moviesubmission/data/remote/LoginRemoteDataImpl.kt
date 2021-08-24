package com.franzandel.moviesubmission.data.remote

import com.franzandel.moviesubmission.core.data.wrapper.Result
import com.franzandel.moviesubmission.core.data.wrapper.suspendTryCatch
import com.franzandel.moviesubmission.core.mapper.BaseMapper
import com.franzandel.moviesubmission.core.mapper.RetrofitResMapper
import com.franzandel.moviesubmission.data.remote.model.LoginReqDTO
import com.franzandel.moviesubmission.data.remote.model.LoginResDTO
import com.franzandel.moviesubmission.data.remote.network.LoginNetworkService
import com.franzandel.moviesubmission.domain.model.LoginReq
import javax.inject.Inject

class LoginRemoteDataImpl @Inject constructor(
    private val service: LoginNetworkService,
    private val reqMapper: BaseMapper<LoginReq, LoginReqDTO>,
    private val resMapper: RetrofitResMapper<LoginResDTO, String>,
) : LoginRemoteData {

    override suspend fun login(loginReq: LoginReq): Result<String> =
        suspendTryCatch {
            val loginReqDTO = reqMapper.map(loginReq)
            val loginResponse = service.login(loginReqDTO)
            resMapper.mapResponse(loginResponse)
        }
}