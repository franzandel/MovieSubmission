package com.franzandel.moviesubmission.data.local.mapper

import com.franzandel.moviesubmission.core.mapper.BaseMapper
import com.franzandel.moviesubmission.data.local.entity.FavouriteMovieEntity
import com.franzandel.moviesubmission.domain.model.FavouriteMovieRes

/**
 * Created by Franz Andel on 12/08/21.
 * Android Engineer
 */

class FavouriteMoviesResponseMapper :
    BaseMapper<List<FavouriteMovieEntity>, List<FavouriteMovieRes>>() {

    override fun map(dataModel: List<FavouriteMovieEntity>): List<FavouriteMovieRes> =
        dataModel.map {
            FavouriteMovieRes(
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