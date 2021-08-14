package com.franzandel.moviesubmission.data.remote

import com.franzandel.moviesubmission.core.data.wrapper.Result
import com.franzandel.moviesubmission.core.external.extension.gson
import com.franzandel.moviesubmission.core.mapper.RetrofitResMapper
import com.franzandel.moviesubmission.data.remote.mapper.RetrofitGenresResMapper
import com.franzandel.moviesubmission.data.remote.mapper.RetrofitMoviesResMapper
import com.franzandel.moviesubmission.data.remote.model.GenresResDTO
import com.franzandel.moviesubmission.data.remote.model.MoviesResDTO
import com.franzandel.moviesubmission.data.remote.network.MoviesNetworkService
import com.franzandel.moviesubmission.domain.model.GenreRes
import com.franzandel.moviesubmission.domain.model.MovieRes
import com.franzandel.moviesubmission.utils.RetrofitUtils
import com.franzandel.moviesubmission.utils.enqueueResponse
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Franz Andel on 14/08/21.
 * Android Engineer
 */

class MoviesRemoteDataImplTest {

    private val mockWebServer = MockWebServer()
    private val service = Retrofit.Builder()
        .baseUrl(mockWebServer.url(""))
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(MoviesNetworkService::class.java)

    private lateinit var remoteData: MoviesRemoteData

    private val moviesResMapper: RetrofitResMapper<MoviesResDTO, List<MovieRes>> =
        RetrofitMoviesResMapper(gson)

    private val genreResMapper: RetrofitResMapper<GenresResDTO, List<GenreRes>> =
        RetrofitGenresResMapper(gson)

    @Before
    fun setUp() {
        remoteData = MoviesRemoteDataImpl(service, moviesResMapper, genreResMapper)
    }

    @Test
    fun `get movies response success`() {
        runBlocking {
            mockWebServer.enqueueResponse("movies_response.json")
            val fakeMoviesRes = RetrofitUtils.getMoviesResFromJson()

            val moviesRes = remoteData.getMovies()
            val result = moviesRes as Result.Success
            assertNotNull(result)
            assertEquals(fakeMoviesRes.size, result.data.size)
        }
    }

    @Test
    fun `get movies response failed`() {
        runBlocking {
            val fakeErrorCode = 404
            mockWebServer.enqueueResponse(
                fileName = "error_response.json",
                responseCode = fakeErrorCode
            )

            val moviesRes = remoteData.getMovies()
            val result = moviesRes as Result.Error
            assertNotNull(result)
            assertEquals(fakeErrorCode, result.code)
        }
    }

    @Test
    fun `get genres response success`() {
        runBlocking {
            mockWebServer.enqueueResponse("genres_response.json")
            val fakeGenresRes = RetrofitUtils.getGenresResFromJson()

            val moviesRes = remoteData.getGenres()
            val result = moviesRes as Result.Success
            assertNotNull(result)
            assertEquals(fakeGenresRes.size, result.data.size)
        }
    }

    @Test
    fun `get genres response failed`() {
        runBlocking {
            val fakeErrorCode = 405
            mockWebServer.enqueueResponse(
                fileName = "error_response.json",
                responseCode = fakeErrorCode
            )

            val genresRes = remoteData.getGenres()
            val result = genresRes as Result.Error
            assertNotNull(result)
            assertEquals(fakeErrorCode, result.code)
        }
    }
}