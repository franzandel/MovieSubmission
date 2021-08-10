package com.franzandel.moviesubmission.data.remote

import com.franzandel.moviesubmission.core.data.wrapper.Result
import com.franzandel.moviesubmission.domain.model.MovieRes

/**
 * Created by Franz Andel on 10/08/21.
 * Android Engineer
 */

interface MoviesRemoteData {
    suspend fun getMovies(): Result<List<MovieRes>>
}