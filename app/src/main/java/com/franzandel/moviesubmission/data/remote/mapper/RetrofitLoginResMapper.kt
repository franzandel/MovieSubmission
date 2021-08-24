package com.franzandel.moviesubmission.data.remote.mapper

import com.franzandel.moviesubmission.core.mapper.RetrofitResMapper
import com.franzandel.moviesubmission.data.remote.model.LoginResDTO
import com.google.gson.Gson
import javax.inject.Inject

/**
 * Created by Franz Andel on 24/08/21.
 * Android Engineer
 */

class RetrofitLoginResMapper @Inject constructor(gson: Gson) :
    RetrofitResMapper<LoginResDTO, String>(gson) {

    override fun map(dataModel: LoginResDTO): String =
        dataModel.username
}