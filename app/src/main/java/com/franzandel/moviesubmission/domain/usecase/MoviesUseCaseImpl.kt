package com.franzandel.moviesubmission.domain.usecase

import com.franzandel.moviesubmission.core.data.wrapper.Result
import com.franzandel.moviesubmission.domain.model.MovieRes
import com.franzandel.moviesubmission.domain.repository.MoviesRepository

class MoviesUseCaseImpl(private val repository: MoviesRepository) : MoviesUseCase {

    override suspend fun getMovies(): Result<List<MovieRes>> = repository.getMovies()
}