package com.franzandel.moviesubmission.data.remote.mapper

import com.franzandel.moviesubmission.core.mapper.RetrofitResMapper
import com.franzandel.moviesubmission.data.remote.model.GenresResDTO
import com.franzandel.moviesubmission.domain.model.GenreRes
import com.google.gson.Gson

/**
 * Created by Franz Andel on 10/08/21.
 * Android Engineer
 */

class RetrofitGenresResMapper(gson: Gson) : RetrofitResMapper<GenresResDTO, List<GenreRes>>(gson) {

    override fun map(dataModel: GenresResDTO): List<GenreRes> =
        dataModel.genres.map {
            GenreRes(
                id = it.id,
                name = it.name
            )
        }
}