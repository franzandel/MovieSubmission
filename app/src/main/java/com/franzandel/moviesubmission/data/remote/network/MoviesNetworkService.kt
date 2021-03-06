package com.franzandel.moviesubmission.data.remote.network

import com.franzandel.moviesubmission.data.remote.model.GenresResDTO
import com.franzandel.moviesubmission.data.remote.model.MoviesResDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Franz Andel on 10/08/21.
 * Android Engineer
 */

interface MoviesNetworkService {
    @GET("discover/movie")
    suspend fun getMovies(@Query("api_key") apiKey: String): Response<MoviesResDTO>

    @GET("genre/movie/list")
    suspend fun getGenres(@Query("api_key") apiKey: String): Response<GenresResDTO>
}