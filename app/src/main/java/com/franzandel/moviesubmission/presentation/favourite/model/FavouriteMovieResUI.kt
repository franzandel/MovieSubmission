package com.franzandel.moviesubmission.presentation.favourite.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by Franz Andel on 13/08/21.
 * Android Engineer
 */

@Parcelize
data class FavouriteMovieResUI(
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
    val voteCount: Int,
    var isFavourite: Boolean
) : Parcelable