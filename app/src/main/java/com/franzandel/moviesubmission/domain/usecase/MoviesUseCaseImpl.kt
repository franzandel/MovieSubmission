package com.franzandel.moviesubmission.domain.usecase

import com.franzandel.moviesubmission.core.data.wrapper.Result
import com.franzandel.moviesubmission.domain.model.MovieGenreRes
import com.franzandel.moviesubmission.domain.model.MovieRequest
import com.franzandel.moviesubmission.domain.repository.MoviesRepository
import javax.inject.Inject

class MoviesUseCaseImpl @Inject constructor(private val repository: MoviesRepository) :
    MoviesUseCase {

    override suspend fun getMovies(): Result<List<MovieGenreRes>> = repository.getMovies()

    override suspend fun insertFavouriteMovie(movieRequest: MovieRequest): Result<Unit> =
        repository.insertFavouriteMovie(movieRequest)

    override suspend fun deleteFavouriteMovie(movieRequest: MovieRequest): Result<Unit> =
        repository.deleteFavouriteMovie(movieRequest)
}