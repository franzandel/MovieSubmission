package com.franzandel.moviesubmission.data.local

import com.franzandel.moviesubmission.core.data.wrapper.Result
import com.franzandel.moviesubmission.domain.model.MovieRequest
import com.franzandel.moviesubmission.domain.model.MovieResponse

/**
 * Created by Franz Andel on 11/08/21.
 * Android Engineer
 */

interface MoviesLocalData {
    suspend fun insertFavouriteMovie(movieRequest: MovieRequest): Result<Unit>
    suspend fun deleteFavouriteMovie(movieRequest: MovieRequest): Result<Unit>
    suspend fun getFavouriteMovie(id: Int): Result<MovieResponse>
    suspend fun getFavouriteMovies(): Result<List<MovieResponse>>
}