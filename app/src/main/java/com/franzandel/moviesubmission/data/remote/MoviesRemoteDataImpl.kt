package com.franzandel.moviesubmission.data.remote

import com.franzandel.moviesubmission.BuildConfig
import com.franzandel.moviesubmission.core.data.wrapper.Result
import com.franzandel.moviesubmission.core.data.wrapper.data
import com.franzandel.moviesubmission.core.data.wrapper.suspendTryCatch
import com.franzandel.moviesubmission.core.mapper.RetrofitResMapper
import com.franzandel.moviesubmission.data.remote.mapper.GenresResMapper
import com.franzandel.moviesubmission.data.remote.mapper.MoviesGenresResMapper
import com.franzandel.moviesubmission.data.remote.model.GenresResDTO
import com.franzandel.moviesubmission.data.remote.model.MoviesResDTO
import com.franzandel.moviesubmission.data.remote.network.MoviesNetworkService
import com.franzandel.moviesubmission.domain.model.GenreRes
import com.franzandel.moviesubmission.domain.model.MovieGenreRes
import com.franzandel.moviesubmission.domain.model.MovieRes
import javax.inject.Inject

class MoviesRemoteDataImpl @Inject constructor(
    private val service: MoviesNetworkService,
    private val moviesResMapper: RetrofitResMapper<MoviesResDTO, List<MovieRes>>,
    private val genreResMapper: RetrofitResMapper<GenresResDTO, List<GenreRes>>
) : MoviesRemoteData {

    override suspend fun getMovies(): Result<List<MovieGenreRes>> =
        suspendTryCatch {
            val moviesResponse = service.getMovies(BuildConfig.MOVIE_DB_API_KEY)
            val genresResponse = service.getGenres(BuildConfig.MOVIE_DB_API_KEY)
            val moviesRes = moviesResMapper.mapResponse(moviesResponse)
            val genresRes = genreResMapper.mapResponse(genresResponse)

            val genresResMapper = GenresResMapper(genresRes.data)
            val moviesGenresResMapper = MoviesGenresResMapper(genresResMapper)
            val moviesGenresRes = moviesGenresResMapper.map(moviesRes.data)

            Result.Success(moviesGenresRes)
        }
}