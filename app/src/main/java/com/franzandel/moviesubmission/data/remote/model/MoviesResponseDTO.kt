package com.franzandel.moviesubmission.data.remote.model

import com.google.gson.annotations.SerializedName

data class MoviesResponseDTO(
    val page: Int,
    @SerializedName("results")
    val movies: List<MovieDTO>,
    val totalPages: Int,
    val totalResults: Int
)