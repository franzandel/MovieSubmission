package com.franzandel.moviesubmission.presentation.favouritemovies.mapper

import com.franzandel.moviesubmission.core.mapper.BaseMapper
import com.franzandel.moviesubmission.domain.model.FavouriteMovieRes
import com.franzandel.moviesubmission.presentation.favouritemovies.model.FavouriteMovieResUI

/**
 * Created by Franz Andel on 13/08/21.
 * Android Engineer
 */

class FavouriteMovieResUIMapper : BaseMapper<List<FavouriteMovieRes>, List<FavouriteMovieResUI>>() {

    override fun map(dataModel: List<FavouriteMovieRes>): List<FavouriteMovieResUI> =
        dataModel.map {
            FavouriteMovieResUI(
                adult = it.adult,
                backdropPath = it.backdropPath,
                genres = it.genres,
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
                voteCount = it.voteCount,
                isFavourite = true
            )
        }
}