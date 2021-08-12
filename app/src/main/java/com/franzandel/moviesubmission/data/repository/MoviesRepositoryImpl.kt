package com.franzandel.moviesubmission.data.repository

import com.franzandel.moviesubmission.core.data.wrapper.Result
import com.franzandel.moviesubmission.core.data.wrapper.data
import com.franzandel.moviesubmission.core.data.wrapper.suspendTryCatch
import com.franzandel.moviesubmission.data.local.MoviesLocalData
import com.franzandel.moviesubmission.data.remote.MoviesRemoteData
import com.franzandel.moviesubmission.data.remote.mapper.GenresResMapper
import com.franzandel.moviesubmission.data.remote.mapper.MoviesGenresResMapper
import com.franzandel.moviesubmission.domain.model.MovieGenreRes
import com.franzandel.moviesubmission.domain.model.MovieRequest
import com.franzandel.moviesubmission.domain.repository.MoviesRepository
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val remoteData: MoviesRemoteData,
    private val localData: MoviesLocalData
) : MoviesRepository {

    override suspend fun getMovies(): Result<List<MovieGenreRes>> =
        suspendTryCatch {
            when (val moviesRes = remoteData.getMovies()) {
                is Result.Success -> {
                    val genresRes = remoteData.getGenres()
                    val genresResMapper = GenresResMapper(genresRes.data)
                    val moviesGenresResMapper = MoviesGenresResMapper(genresResMapper)
                    val moviesGenresRes = moviesGenresResMapper.map(moviesRes.data)
                    Result.Success(moviesGenresRes)
                }
                is Result.Error -> moviesRes
            }
        }

    override suspend fun insertFavouriteMovie(movieRequest: MovieRequest): Result<Unit> =
        localData.insertFavouriteMovie(movieRequest)

    override suspend fun deleteFavouriteMovie(movieRequest: MovieRequest): Result<Unit> =
        localData.deleteFavouriteMovie(movieRequest)
}