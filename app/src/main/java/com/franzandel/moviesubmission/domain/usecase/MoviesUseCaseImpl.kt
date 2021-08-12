package com.franzandel.moviesubmission.domain.usecase

import com.franzandel.moviesubmission.core.data.wrapper.Result
import com.franzandel.moviesubmission.core.data.wrapper.data
import com.franzandel.moviesubmission.core.data.wrapper.suspendTryCatch
import com.franzandel.moviesubmission.domain.model.FavouriteMovieReq
import com.franzandel.moviesubmission.domain.model.FavouriteMovieRes
import com.franzandel.moviesubmission.domain.model.MovieGenreRes
import com.franzandel.moviesubmission.domain.repository.MoviesRepository
import javax.inject.Inject

class MoviesUseCaseImpl @Inject constructor(private val repository: MoviesRepository) :
    MoviesUseCase {

    override suspend fun getMovies(): Result<List<MovieGenreRes>> =
        suspendTryCatch {
            val favouriteMovies = repository.getFavouriteMovies().data
            val moviesGenresRes = repository.getMovies().data
            Result.Success(processFavouriteMovies(favouriteMovies, moviesGenresRes))
        }

    override suspend fun insertFavouriteMovie(favouriteMovieReq: FavouriteMovieReq): Result<Unit> =
        repository.insertFavouriteMovie(favouriteMovieReq)

    override suspend fun deleteFavouriteMovie(favouriteMovieReq: FavouriteMovieReq): Result<Unit> =
        repository.deleteFavouriteMovie(favouriteMovieReq)

    private fun processFavouriteMovies(
        favouriteMovies: List<FavouriteMovieRes>, moviesGenresRes: List<MovieGenreRes>
    ): List<MovieGenreRes> {
        favouriteMovies.forEach { movieResponse ->
            moviesGenresRes.find { movieGenreRes ->
                movieGenreRes.id == movieResponse.id
            }?.isFavourite = true
        }

        return moviesGenresRes
    }
}