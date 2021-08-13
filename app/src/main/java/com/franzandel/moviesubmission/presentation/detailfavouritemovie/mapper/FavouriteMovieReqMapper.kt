package com.franzandel.moviesubmission.presentation.detailfavouritemovie.mapper

import com.franzandel.moviesubmission.core.mapper.BaseMapper
import com.franzandel.moviesubmission.domain.model.FavouriteMovieReq
import com.franzandel.moviesubmission.presentation.favouritemovies.model.FavouriteMovieResUI
import javax.inject.Inject

/**
 * Created by Franz Andel on 13/08/21.
 * Android Engineer
 */

class FavouriteMovieReqMapper @Inject constructor() :
    BaseMapper<FavouriteMovieResUI, FavouriteMovieReq>() {

    override fun map(dataModel: FavouriteMovieResUI): FavouriteMovieReq =
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