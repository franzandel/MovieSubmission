package com.franzandel.moviesubmission.domain.model

/**
 * Created by Franz Andel on 12/08/21.
 * Android Engineer
 */

data class MovieResponse(
    val id: Int,
    val adult: Boolean,
    val backdropPath: String,
    val genres: String,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
)