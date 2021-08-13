package com.franzandel.moviesubmission.presentation.popularmovies.mapper

import com.franzandel.moviesubmission.core.mapper.BaseMapper
import com.franzandel.moviesubmission.domain.model.FavouriteMovieReq
import com.franzandel.moviesubmission.presentation.popularmovies.model.PopularMovieResUI
import javax.inject.Inject

/**
 * Created by Franz Andel on 12/08/21.
 * Android Engineer
 */

class FavouriteMovieReqMapper @Inject constructor() :
    BaseMapper<PopularMovieResUI, FavouriteMovieReq>() {

    override fun map(dataModel: PopularMovieResUI): FavouriteMovieReq =
        with(dataModel) {
            FavouriteMovieReq(
                adult = adult,
                backdropPath = backdropPath,
                genres = genres,
                id = id,
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