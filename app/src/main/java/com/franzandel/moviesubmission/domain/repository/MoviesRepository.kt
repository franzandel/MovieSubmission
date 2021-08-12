package com.franzandel.moviesubmission.domain.repository

import com.franzandel.moviesubmission.core.data.wrapper.Result
import com.franzandel.moviesubmission.domain.model.MovieGenreRes
import com.franzandel.moviesubmission.domain.model.MovieRequest

/**
 * Created by Franz Andel on 11/08/21.
 * Android Engineer
 */

interface MoviesRepository {
    suspend fun getMovies(): Result<List<MovieGenreRes>>
    suspend fun insertFavouriteMovie(movieRequest: MovieRequest): Result<Unit>
}