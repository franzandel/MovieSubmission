package com.franzandel.moviesubmission.core.data.entity

import com.google.gson.annotations.SerializedName

data class MoviesErrorResDTO(
    @SerializedName("status_code")
    val statusCode: Int,
    @SerializedName("status_message")
    val statusMessage: String,
    val success: Boolean
)