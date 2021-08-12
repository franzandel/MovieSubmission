package com.franzandel.moviesubmission.data.local

import com.franzandel.moviesubmission.core.data.wrapper.Result
import com.franzandel.moviesubmission.core.data.wrapper.suspendTryCatch
import com.franzandel.moviesubmission.core.mapper.BaseMapper
import com.franzandel.moviesubmission.core.mapper.mapLocalResponse
import com.franzandel.moviesubmission.data.consts.DatabaseConst
import com.franzandel.moviesubmission.data.local.dao.FavouriteMoviesDao
import com.franzandel.moviesubmission.data.local.entity.FavouriteMovieEntity
import com.franzandel.moviesubmission.domain.model.FavouriteMovieReq
import com.franzandel.moviesubmission.domain.model.FavouriteMovieRes
import javax.inject.Inject

class MoviesLocalDataImpl @Inject constructor(
    private val dao: FavouriteMoviesDao,
    private val requestMapper: BaseMapper<FavouriteMovieReq, FavouriteMovieEntity>,
    private val responseMapper: BaseMapper<FavouriteMovieEntity, FavouriteMovieRes>,
    private val responsesMapper: BaseMapper<List<FavouriteMovieEntity>, List<FavouriteMovieRes>>
) : MoviesLocalData {

    override suspend fun insertFavouriteMovie(favouriteMovieReq: FavouriteMovieReq): Result<Unit> =
        suspendTryCatch {
            val favouriteMovieEntity = requestMapper.map(favouriteMovieReq)
            val result = dao.insertFavouriteMovie(favouriteMovieEntity)
            mapLocalResponse(result, Unit, DatabaseConst.ERROR_INSERT_TO_DB)
        }

    override suspend fun deleteFavouriteMovie(favouriteMovieReq: FavouriteMovieReq): Result<Unit> =
        suspendTryCatch {
            val favouriteMovieEntity = requestMapper.map(favouriteMovieReq)
            val result = dao.deleteFavouriteMovie(favouriteMovieEntity)
            mapLocalResponse(result, Unit, DatabaseConst.ERROR_DELETE_FROM_DB)
        }

    override suspend fun getFavouriteMovie(id: Int): Result<FavouriteMovieRes> =
        suspendTryCatch {
            val favouriteMovieEntity = dao.getFavouriteMovie(id)
            if (favouriteMovieEntity == null)
                Result.Error(Exception(DatabaseConst.NO_DATA_FOUND))
            else
                Result.Success(responseMapper.map(favouriteMovieEntity))
        }

    override suspend fun getFavouriteMovies(): Result<List<FavouriteMovieRes>> =
        suspendTryCatch {
            val favouriteMoviesRes = responsesMapper.map(dao.getFavouriteMovies())
            Result.Success(favouriteMoviesRes)
        }
}