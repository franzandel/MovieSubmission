package com.franzandel.moviesubmission.domain.usecase

import com.franzandel.moviesubmission.core.data.wrapper.Result
import com.franzandel.moviesubmission.domain.model.FavouriteMovieReq
import com.franzandel.moviesubmission.domain.model.FavouriteMovieRes
import com.franzandel.moviesubmission.domain.model.MovieGenreRes

/**
 * Created by Franz Andel on 11/08/21.
 * Android Engineer
 */

interface MoviesUseCase {
    suspend fun getMovies(): Result<List<MovieGenreRes>>
    suspend fun insertFavouriteMovie(favouriteMovieReq: FavouriteMovieReq): Result<Unit>
    suspend fun deleteFavouriteMovie(favouriteMovieReq: FavouriteMovieReq): Result<Unit>
    suspend fun getFavouriteMovies(): Result<List<FavouriteMovieRes>>
}