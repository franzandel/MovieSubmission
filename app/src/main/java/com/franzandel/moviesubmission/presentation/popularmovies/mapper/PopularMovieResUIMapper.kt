package com.franzandel.moviesubmission.presentation.popularmovies.mapper

import com.franzandel.moviesubmission.core.mapper.BaseMapper
import com.franzandel.moviesubmission.domain.model.GenreRes
import com.franzandel.moviesubmission.domain.model.MovieGenreRes
import com.franzandel.moviesubmission.presentation.popularmovies.model.PopularMovieResUI

/**
 * Created by Franz Andel on 11/08/21.
 * Android Engineer
 */

class PopularMovieResUIMapper(private val genresResUIMapper: BaseMapper<List<GenreRes>, String>) :
    BaseMapper<List<MovieGenreRes>, List<PopularMovieResUI>>() {

    override fun map(dataModel: List<MovieGenreRes>): List<PopularMovieResUI> =
        dataModel.map {
            PopularMovieResUI(
                adult = it.adult,
                backdropPath = it.backdropPath,
                genres = genresResUIMapper.map(it.genresRes),
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
        }.sortedByDescending {
            it.popularity
        }
}