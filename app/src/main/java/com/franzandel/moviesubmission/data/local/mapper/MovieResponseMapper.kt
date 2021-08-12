package com.franzandel.moviesubmission.data.local.mapper

import com.franzandel.moviesubmission.core.mapper.BaseMapper
import com.franzandel.moviesubmission.data.local.entity.MovieEntity
import com.franzandel.moviesubmission.domain.model.MovieResponse
import javax.inject.Inject

/**
 * Created by Franz Andel on 12/08/21.
 * Android Engineer
 */

class MovieResponseMapper @Inject constructor() : BaseMapper<MovieEntity, MovieResponse>() {

    override fun map(dataModel: MovieEntity): MovieResponse =
        with(dataModel) {
            MovieResponse(
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