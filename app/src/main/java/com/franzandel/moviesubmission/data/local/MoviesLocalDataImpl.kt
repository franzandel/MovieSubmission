package com.franzandel.moviesubmission.data.local

import com.franzandel.moviesubmission.core.data.wrapper.Result
import com.franzandel.moviesubmission.core.data.wrapper.suspendTryCatch
import com.franzandel.moviesubmission.core.mapper.BaseMapper
import com.franzandel.moviesubmission.core.mapper.mapLocalResponse
import com.franzandel.moviesubmission.data.consts.DatabaseConst
import com.franzandel.moviesubmission.data.local.dao.FavouriteMoviesDao
import com.franzandel.moviesubmission.data.local.entity.MovieEntity
import com.franzandel.moviesubmission.domain.model.MovieRequest
import com.franzandel.moviesubmission.domain.model.MovieResponse
import javax.inject.Inject

class MoviesLocalDataImpl @Inject constructor(
    private val dao: FavouriteMoviesDao,
    private val requestMapper: BaseMapper<MovieRequest, MovieEntity>,
    private val responseMapper: BaseMapper<MovieEntity, MovieResponse>,
    private val responsesMapper: BaseMapper<List<MovieEntity>, List<MovieResponse>>
) : MoviesLocalData {

    override suspend fun insertFavouriteMovie(movieRequest: MovieRequest): Result<Unit> =
        suspendTryCatch {
            val movieEntity = requestMapper.map(movieRequest)
            val result = dao.insertFavouriteMovie(movieEntity)
            mapLocalResponse(result, Unit, DatabaseConst.ERROR_INSERT_TO_DB)
        }

    override suspend fun deleteFavouriteMovie(movieRequest: MovieRequest): Result<Unit> =
        suspendTryCatch {
            val movieEntity = requestMapper.map(movieRequest)
            val result = dao.deleteFavouriteMovie(movieEntity)
            mapLocalResponse(result, Unit, DatabaseConst.ERROR_DELETE_FROM_DB)
        }

    override suspend fun getFavouriteMovie(id: Int): Result<MovieResponse> =
        suspendTryCatch {
            val movieEntity = dao.getFavouriteMovie(id)
            if (movieEntity == null)
                Result.Error(Exception(DatabaseConst.NO_DATA_FOUND))
            else
                Result.Success(responseMapper.map(movieEntity))
        }

    override suspend fun getFavouriteMovies(): Result<List<MovieResponse>> =
        suspendTryCatch {
            val moviesRequest = responsesMapper.map(dao.getFavouriteMovies())
            Result.Success(moviesRequest)
        }
}