package com.franzandel.moviesubmission.data.local.mapper

import com.franzandel.moviesubmission.core.mapper.BaseMapper
import com.franzandel.moviesubmission.data.local.entity.FavouriteMovieEntity
import com.franzandel.moviesubmission.domain.model.FavouriteMovieReq
import javax.inject.Inject

/**
 * Created by Franz Andel on 11/08/21.
 * Android Engineer
 */

class FavouriteMovieEntityMapper @Inject constructor() :
    BaseMapper<FavouriteMovieReq, FavouriteMovieEntity>() {

    override fun map(dataModel: FavouriteMovieReq): FavouriteMovieEntity =
        with(dataModel) {
            FavouriteMovieEntity(
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