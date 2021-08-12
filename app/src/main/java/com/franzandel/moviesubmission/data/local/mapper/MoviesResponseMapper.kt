package com.franzandel.moviesubmission.data.local.mapper

import com.franzandel.moviesubmission.core.mapper.BaseMapper
import com.franzandel.moviesubmission.data.local.entity.MovieEntity
import com.franzandel.moviesubmission.domain.model.MovieResponse

/**
 * Created by Franz Andel on 12/08/21.
 * Android Engineer
 */

class MoviesResponseMapper : BaseMapper<List<MovieEntity>, List<MovieResponse>>() {

    override fun map(dataModel: List<MovieEntity>): List<MovieResponse> =
        dataModel.map {
            MovieResponse(
                id = it.id,
                adult = it.adult,
                backdropPath = it.backdropPath,
                genres = it.genres,
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