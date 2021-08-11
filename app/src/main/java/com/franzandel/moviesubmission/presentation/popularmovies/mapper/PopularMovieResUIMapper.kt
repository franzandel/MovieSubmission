package com.franzandel.moviesubmission.presentation.popularmovies.mapper

import com.franzandel.moviesubmission.core.mapper.BaseMapper
import com.franzandel.moviesubmission.domain.model.MovieRes
import com.franzandel.moviesubmission.presentation.popularmovies.model.PopularMovieResUI

/**
 * Created by Franz Andel on 11/08/21.
 * Android Engineer
 */

class PopularMovieResUIMapper : BaseMapper<List<MovieRes>, List<PopularMovieResUI>>() {

    override fun map(dataModel: List<MovieRes>): List<PopularMovieResUI> =
        dataModel.map {
            PopularMovieResUI(
                adult = it.adult,
                backdropPath = it.backdropPath,
                genreIds = it.genreIds,
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