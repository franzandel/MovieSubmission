package com.franzandel.moviesubmission.data.repository

import com.franzandel.moviesubmission.core.data.wrapper.Result
import com.franzandel.moviesubmission.data.local.MoviesLocalData
import com.franzandel.moviesubmission.data.remote.MoviesRemoteData
import com.franzandel.moviesubmission.domain.repository.MoviesRepository
import com.franzandel.moviesubmission.utils.RetrofitUtils
import com.franzandel.moviesubmission.utils.RoomUtils
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

/**
 * Created by Franz Andel on 14/08/21.
 * Android Engineer
 */

@ExperimentalCoroutinesApi
class MoviesRepositoryImplTest {

    private val remoteData: MoviesRemoteData = mockk(relaxed = true)
    private val localData: MoviesLocalData = mockk(relaxed = true)

    private lateinit var repository: MoviesRepository

    @Before
    fun setUp() {
        repository = MoviesRepositoryImpl(remoteData, localData)
    }

    @Test
    fun `insert favourite movie success`() {
        runBlockingTest {
            val fakeFavouriteMovieReq = RoomUtils.getFavouriteMovieReq()
            val fakeInsertResponse = Unit

            coEvery { localData.insertFavouriteMovie(fakeFavouriteMovieReq) } returns Result.Success(
                fakeInsertResponse
            )

            val insertFavouriteMovieResults = repository.insertFavouriteMovie(fakeFavouriteMovieReq)
            val result = insertFavouriteMovieResults as Result.Success
            assertNotNull(insertFavouriteMovieResults)
            assertEquals(fakeInsertResponse, result.data)
        }
    }

    @Test
    fun `insert favourite movie failed`() {
        runBlockingTest {
            val fakeFavouriteMovieReq = RoomUtils.getFavouriteMovieReq()
            val failedResponse = RoomUtils.ERROR_INSERT_TO_DB

            coEvery { localData.insertFavouriteMovie(fakeFavouriteMovieReq) } returns Result.Error(
                Exception(failedResponse)
            )

            val insertFavouriteMovieResults = repository.insertFavouriteMovie(fakeFavouriteMovieReq)
            val result = insertFavouriteMovieResults as Result.Error
            assertNotNull(insertFavouriteMovieResults)
            assertEquals(failedResponse, result.error.message)
        }
    }

    @Test
    fun `delete favourite movie success`() {
        runBlockingTest {
            val fakeFavouriteMovieReq = RoomUtils.getFavouriteMovieReq()
            val fakeSuccessResponse = Unit

            coEvery { localData.deleteFavouriteMovie(fakeFavouriteMovieReq) } returns Result.Success(
                fakeSuccessResponse
            )

            val deleteFavouriteMovie = repository.deleteFavouriteMovie(fakeFavouriteMovieReq)
            val result = deleteFavouriteMovie as Result.Success
            assertNotNull(deleteFavouriteMovie)
            assertEquals(fakeSuccessResponse, result.data)
        }
    }

    @Test
    fun `delete favourite movie failed`() {
        runBlockingTest {
            val fakeFavouriteMovieReq = RoomUtils.getFavouriteMovieReq()
            val fakeFailedResponse = RoomUtils.ERROR_DELETE_FROM_DB

            coEvery {
                localData.deleteFavouriteMovie(fakeFavouriteMovieReq)
            } returns Result.Error(Exception(fakeFailedResponse))

            val deleteGamesResults = repository.deleteFavouriteMovie(fakeFavouriteMovieReq)
            val result = deleteGamesResults as Result.Error
            assertNotNull(deleteGamesResults)
            assertEquals(fakeFailedResponse, result.error.message)
        }
    }

    @Test
    fun `get favourite movies found`() {
        runBlockingTest {
            val fakeGamesResult = RoomUtils.getFavouriteMoviesRes()

            coEvery { localData.getFavouriteMovies() } returns Result.Success(fakeGamesResult)

            val favouriteMoviesResults = repository.getFavouriteMovies()
            val result = favouriteMoviesResults as Result.Success
            assertNotNull(favouriteMoviesResults)
            assertEquals(fakeGamesResult.size, result.data.size)
        }
    }

    @Test
    fun `get favourite movies not found`() {
        runBlockingTest {
            val fakeFailedResponse = RoomUtils.NO_DATA_FOUND

            coEvery { localData.getFavouriteMovies() } returns Result.Error(
                Exception(
                    fakeFailedResponse
                )
            )

            val favouriteMoviesResults = repository.getFavouriteMovies()
            val result = favouriteMoviesResults as Result.Error
            assertNotNull(favouriteMoviesResults)
            assertEquals(fakeFailedResponse, result.error.message)
        }
    }

    @Test
    fun `get movies success`() {
        runBlockingTest {
            val fakeMovieRes = RetrofitUtils.getMovieRes()
            val fakeGenreRes = RetrofitUtils.getGenreRes()
            val fakeMoviesGenresRes = RetrofitUtils.getMoviesGenresRes()

            coEvery { remoteData.getMovies() } returns Result.Success(
                fakeMovieRes
            )
            coEvery { remoteData.getGenres() } returns Result.Success(fakeGenreRes)

            val moviesResult = repository.getMovies()
            val result = moviesResult as Result.Success
            assertNotNull(moviesResult)
            assertEquals(fakeMoviesGenresRes, result.data)
        }
    }

    @Test
    fun `get movies failed`() {
        runBlockingTest {
            val fakeFailedResponse = RetrofitUtils.GET_DATA_FAILED

            coEvery {
                remoteData.getMovies()
            } returns Result.Error(Exception(fakeFailedResponse))

            val moviesResult = repository.getMovies()
            val result = moviesResult as Result.Error
            assertNotNull(moviesResult)
            assertEquals(fakeFailedResponse, result.error.message)
        }
    }
}