package com.franzandel.moviesubmission.data.repository

import com.franzandel.moviesubmission.core.data.wrapper.Result
import com.franzandel.moviesubmission.data.remote.MoviesRemoteData
import com.franzandel.moviesubmission.domain.model.MovieRes
import com.franzandel.moviesubmission.domain.repository.MoviesRepository
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(private val remoteData: MoviesRemoteData) :
    MoviesRepository {

    override suspend fun getMovies(): Result<List<MovieRes>> = remoteData.getMovies()
}