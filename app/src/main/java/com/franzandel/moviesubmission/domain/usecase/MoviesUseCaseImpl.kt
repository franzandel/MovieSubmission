package com.franzandel.moviesubmission.domain.usecase

import com.franzandel.moviesubmission.core.data.wrapper.Result
import com.franzandel.moviesubmission.core.data.wrapper.data
import com.franzandel.moviesubmission.core.data.wrapper.suspendTryCatch
import com.franzandel.moviesubmission.domain.model.MovieGenreRes
import com.franzandel.moviesubmission.domain.model.MovieRequest
import com.franzandel.moviesubmission.domain.model.MovieResponse
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

    override suspend fun insertFavouriteMovie(movieRequest: MovieRequest): Result<Unit> =
        repository.insertFavouriteMovie(movieRequest)

    override suspend fun deleteFavouriteMovie(movieRequest: MovieRequest): Result<Unit> =
        repository.deleteFavouriteMovie(movieRequest)

    private fun processFavouriteMovies(
        favouriteMovies: List<MovieResponse>, moviesGenresRes: List<MovieGenreRes>
    ): List<MovieGenreRes> {
        favouriteMovies.forEach { movieResponse ->
            moviesGenresRes.find { movieGenreRes ->
                movieGenreRes.id == movieResponse.id
            }?.isFavourite = true
        }

        return moviesGenresRes
    }
}