package com.franzandel.moviesubmission.data.remote.mapper

import com.franzandel.moviesubmission.core.mapper.BaseMapper
import com.franzandel.moviesubmission.domain.model.GenreRes

/**
 * Created by Franz Andel on 11/08/21.
 * Android Engineer
 */

class GenresResMapper(
    private val genresRes: List<GenreRes>
) : BaseMapper<List<Int>, List<GenreRes>>() {

    override fun map(dataModel: List<Int>): List<GenreRes> =
        dataModel.map { genreId ->
            GenreRes(
                id = genreId,
                name = genresRes.find { genresRes ->
                    genresRes.id == genreId
                }?.name.toString()
            )
        }
}