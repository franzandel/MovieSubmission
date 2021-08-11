package com.franzandel.moviesubmission.data.remote.model

import androidx.annotation.Keep

@Keep
data class GenresResDTO(
    val genres: List<GenreResDTO>
)