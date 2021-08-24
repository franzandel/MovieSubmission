package com.franzandel.moviesubmission.data.remote.network

import com.franzandel.moviesubmission.data.remote.model.LoginReqDTO
import com.franzandel.moviesubmission.data.remote.model.LoginResDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by Franz Andel on 24/08/21.
 * Android Engineer
 */

interface LoginNetworkService {
    @POST("BYogatama/fantastic-api/login")
    suspend fun login(@Body loginReqDTO: LoginReqDTO): Response<LoginResDTO>
}