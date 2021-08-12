package com.franzandel.moviesubmission.data.local.mapper

import com.franzandel.moviesubmission.core.mapper.BaseMapper
import com.franzandel.moviesubmission.data.local.entity.MovieEntity
import com.franzandel.moviesubmission.domain.model.MovieRequest
import javax.inject.Inject

/**
 * Created by Franz Andel on 11/08/21.
 * Android Engineer
 */

class MovieEntityMapper @Inject constructor() : BaseMapper<MovieRequest, MovieEntity>() {

    override fun map(dataModel: MovieRequest): MovieEntity =
        with(dataModel) {
            MovieEntity(
                id = id,
                adult = adult,
                backdropPath = backdropPath,
                genres = genres,
                originalLanguage = originalLanguage,
                originalTitle = originalTitle,
                overview = overview,
                popularity = popularity,
                posterPath = posterPath,
                releaseDate = releaseDate,
                title = title,
                video = video,
                voteAverage = voteAverage,
                voteCount = voteCount
            )
        }
}