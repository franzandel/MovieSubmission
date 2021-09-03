package com.franzandel.moviesubmission.data.remote.mapper

import com.franzandel.moviesubmission.core.mapper.RetrofitResMapper
import com.franzandel.moviesubmission.data.remote.model.MoviesResDTO
import com.franzandel.moviesubmission.domain.model.MovieRes
import com.google.gson.Gson

/**
 * Created by Franz Andel on 10/08/21.
 * Android Engineer
 */

class RetrofitMoviesResMapper(gson: Gson) : RetrofitResMapper<MoviesResDTO, List<MovieRes>>(gson) {

    override fun map(dataModel: MoviesResDTO): List<MovieRes> =
        dataModel.movies.map {
            MovieRes(
                adult = it.adult,
                backdropPath = it.backdropPath ?: "",
                genreIds = it.genreIds,
                id = it.id,
                originalLanguage = it.originalLanguage,
                originalTitle = it.originalTitle,
                overview = it.overview,
                popularity = it.popularity,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate,
                title = it.title,
                video = it.video,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount
            )
        }
}