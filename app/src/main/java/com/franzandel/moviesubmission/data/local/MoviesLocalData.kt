package com.franzandel.moviesubmission.data.local

import com.franzandel.moviesubmission.core.data.wrapper.Result
import com.franzandel.moviesubmission.domain.model.MovieRequest

/**
 * Created by Franz Andel on 11/08/21.
 * Android Engineer
 */

interface MoviesLocalData {
    suspend fun insertFavouriteMovie(movieRequest: MovieRequest): Result<Unit>
}