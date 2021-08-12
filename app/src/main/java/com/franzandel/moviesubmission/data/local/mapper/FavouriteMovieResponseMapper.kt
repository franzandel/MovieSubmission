package com.franzandel.moviesubmission.data.local.mapper

import com.franzandel.moviesubmission.core.mapper.BaseMapper
import com.franzandel.moviesubmission.data.local.entity.FavouriteMovieEntity
import com.franzandel.moviesubmission.domain.model.FavouriteMovieRes
import javax.inject.Inject

/**
 * Created by Franz Andel on 12/08/21.
 * Android Engineer
 */

class FavouriteMovieResponseMapper @Inject constructor() :
    BaseMapper<FavouriteMovieEntity, FavouriteMovieRes>() {

    override fun map(dataModel: FavouriteMovieEntity): FavouriteMovieRes =
        with(dataModel) {
            FavouriteMovieRes(
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