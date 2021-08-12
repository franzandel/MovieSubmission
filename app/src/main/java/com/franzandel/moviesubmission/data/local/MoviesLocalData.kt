package com.franzandel.moviesubmission.data.local

import com.franzandel.moviesubmission.core.data.wrapper.Result
import com.franzandel.moviesubmission.domain.model.FavouriteMovieReq
import com.franzandel.moviesubmission.domain.model.FavouriteMovieRes

/**
 * Created by Franz Andel on 11/08/21.
 * Android Engineer
 */

interface MoviesLocalData {
    suspend fun insertFavouriteMovie(favouriteMovieReq: FavouriteMovieReq): Result<Unit>
    suspend fun deleteFavouriteMovie(favouriteMovieReq: FavouriteMovieReq): Result<Unit>
    suspend fun getFavouriteMovie(id: Int): Result<FavouriteMovieRes>
    suspend fun getFavouriteMovies(): Result<List<FavouriteMovieRes>>
}