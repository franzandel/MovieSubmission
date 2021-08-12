package com.franzandel.moviesubmission.data.repository

import com.franzandel.moviesubmission.core.data.wrapper.Result
import com.franzandel.moviesubmission.core.data.wrapper.data
import com.franzandel.moviesubmission.core.data.wrapper.suspendTryCatch
import com.franzandel.moviesubmission.data.local.MoviesLocalData
import com.franzandel.moviesubmission.data.remote.MoviesRemoteData
import com.franzandel.moviesubmission.data.remote.mapper.GenresResMapper
import com.franzandel.moviesubmission.data.remote.mapper.MoviesGenresResMapper
import com.franzandel.moviesubmission.domain.model.FavouriteMovieReq
import com.franzandel.moviesubmission.domain.model.FavouriteMovieRes
import com.franzandel.moviesubmission.domain.model.MovieGenreRes
import com.franzandel.moviesubmission.domain.repository.MoviesRepository
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val remoteData: MoviesRemoteData,
    private val localData: MoviesLocalData
) : MoviesRepository {

    override suspend fun getMovies(): Result<List<MovieGenreRes>> =
        suspendTryCatch {
            val moviesRes = remoteData.getMovies().data
            val genresRes = remoteData.getGenres().data

            val genresResMapper = GenresResMapper(genresRes)
            val moviesGenresResMapper = MoviesGenresResMapper(genresResMapper)
            val moviesGenresRes = moviesGenresResMapper.map(moviesRes)

            Result.Success(moviesGenresRes)
        }

    override suspend fun insertFavouriteMovie(favouriteMovieReq: FavouriteMovieReq): Result<Unit> =
        localData.insertFavouriteMovie(favouriteMovieReq)

    override suspend fun deleteFavouriteMovie(favouriteMovieReq: FavouriteMovieReq): Result<Unit> =
        localData.deleteFavouriteMovie(favouriteMovieReq)

    override suspend fun getFavouriteMovies(): Result<List<FavouriteMovieRes>> =
        localData.getFavouriteMovies()
}