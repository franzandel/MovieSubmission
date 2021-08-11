package com.franzandel.moviesubmission.presentation.topratedmovies.mapper

import com.franzandel.moviesubmission.core.mapper.BaseMapper
import com.franzandel.moviesubmission.domain.model.MovieRes
import com.franzandel.moviesubmission.presentation.topratedmovies.model.TopRatedMovieResUI

/**
 * Created by Franz Andel on 11/08/21.
 * Android Engineer
 */

class TopRatedMovieResUIMapper : BaseMapper<List<MovieRes>, List<TopRatedMovieResUI>>() {

    override fun map(dataModel: List<MovieRes>): List<TopRatedMovieResUI> =
        dataModel.map {
            TopRatedMovieResUI(
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
            it.voteAverage
        }
}