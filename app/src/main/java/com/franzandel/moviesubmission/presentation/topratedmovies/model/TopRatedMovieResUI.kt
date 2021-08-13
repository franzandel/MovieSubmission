package com.franzandel.moviesubmission.presentation.topratedmovies.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by Franz Andel on 11/08/21.
 * Android Engineer
 */

@Parcelize
data class TopRatedMovieResUI(
    val adult: Boolean,
    val backdropPath: String,
    val genres: String,
    val id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int,
    var isFavourite: Boolean
) : Parcelable