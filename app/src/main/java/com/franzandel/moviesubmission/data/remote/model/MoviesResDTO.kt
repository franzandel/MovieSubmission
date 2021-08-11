package com.franzandel.moviesubmission.data.remote.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class MoviesResDTO(
    val page: Int,
    @SerializedName("results")
    val movies: List<MovieResDTO>,
    val totalPages: Int,
    val totalResults: Int
)