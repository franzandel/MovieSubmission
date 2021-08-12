package com.franzandel.moviesubmission.data.local

import com.franzandel.moviesubmission.core.data.wrapper.Result
import com.franzandel.moviesubmission.core.data.wrapper.suspendTryCatch
import com.franzandel.moviesubmission.core.mapper.BaseMapper
import com.franzandel.moviesubmission.core.mapper.mapLocalResponse
import com.franzandel.moviesubmission.data.consts.DatabaseConst
import com.franzandel.moviesubmission.data.local.dao.FavouriteMoviesDao
import com.franzandel.moviesubmission.data.local.entity.MovieEntity
import com.franzandel.moviesubmission.domain.model.MovieRequest
import javax.inject.Inject

class MoviesLocalDataImpl @Inject constructor(
    private val dao: FavouriteMoviesDao,
    private val mapper: BaseMapper<MovieRequest, MovieEntity>
) : MoviesLocalData {

    override suspend fun insertFavouriteMovie(movieRequest: MovieRequest): Result<Unit> =
        suspendTryCatch {
            val movieEntity = mapper.map(movieRequest)
            val result = dao.insertFavouriteMovie(movieEntity)
            mapLocalResponse(result, Unit, DatabaseConst.ERROR_INSERT_TO_DB)
        }

    override suspend fun deleteFavouriteMovie(movieRequest: MovieRequest): Result<Unit> =
        suspendTryCatch {
            val movieEntity = mapper.map(movieRequest)
            val result = dao.deleteFavouriteMovie(movieEntity)
            mapLocalResponse(result, Unit, DatabaseConst.ERROR_DELETE_FROM_DB)
        }
}