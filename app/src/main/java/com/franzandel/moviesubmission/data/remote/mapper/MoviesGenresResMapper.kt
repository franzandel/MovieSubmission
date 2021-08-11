package com.franzandel.moviesubmission.data.remote.mapper

import com.franzandel.moviesubmission.core.mapper.BaseMapper
import com.franzandel.moviesubmission.domain.model.GenreRes
import com.franzandel.moviesubmission.domain.model.MovieGenreRes
import com.franzandel.moviesubmission.domain.model.MovieRes

/**
 * Created by Franz Andel on 11/08/21.
 * Android Engineer
 */

class MoviesGenresResMapper(
    private val genresResMapper: BaseMapper<List<Int>, List<GenreRes>>
) : BaseMapper<List<MovieRes>, List<MovieGenreRes>>() {

    override fun map(dataModel: List<MovieRes>): List<MovieGenreRes> =
        dataModel.map {
            MovieGenreRes(
                adult = it.adult,
                backdropPath = it.backdropPath,
                genresRes = genresResMapper.map(it.genreIds),
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