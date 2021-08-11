package com.franzandel.moviesubmission.domain.usecase

import com.franzandel.moviesubmission.core.data.wrapper.Result
import com.franzandel.moviesubmission.domain.model.MovieGenreRes

/**
 * Created by Franz Andel on 11/08/21.
 * Android Engineer
 */

interface MoviesUseCase {
    suspend fun getMovies(): Result<List<MovieGenreRes>>
}