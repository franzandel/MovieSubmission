package com.franzandel.moviesubmission.data.remote

import com.franzandel.moviesubmission.BuildConfig
import com.franzandel.moviesubmission.core.data.wrapper.Result
import com.franzandel.moviesubmission.core.data.wrapper.suspendTryCatch
import com.franzandel.moviesubmission.core.mapper.RetrofitResMapper
import com.franzandel.moviesubmission.data.remote.model.MoviesResDTO
import com.franzandel.moviesubmission.data.remote.network.MoviesNetworkService
import com.franzandel.moviesubmission.domain.model.MovieRes
import javax.inject.Inject

class MoviesRemoteDataImpl @Inject constructor(
    private val service: MoviesNetworkService,
    private val mapper: RetrofitResMapper<MoviesResDTO, List<MovieRes>>
) : MoviesRemoteData {

    override suspend fun getMovies(): Result<List<MovieRes>> =
        suspendTryCatch {
            val response = service.getMovies(BuildConfig.MOVIE_DB_API_KEY)
            mapper.mapResponse(response)
        }
}