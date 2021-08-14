package com.franzandel.moviesubmission.domain.usecase

import com.franzandel.moviesubmission.core.data.wrapper.Result
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
class MoviesUseCaseImplTest {

    private val repository: MoviesRepository = mockk(relaxed = true)

    private lateinit var useCase: MoviesUseCase

    @Before
    fun setUp() {
        useCase = MoviesUseCaseImpl(repository)
    }

    @Test
    fun `insert favourite movie success`() {
        runBlockingTest {
            val fakeFavouriteMovieReq = RoomUtils.getFavouriteMovieReq()
            val fakeInsertResponse = Unit

            coEvery { repository.insertFavouriteMovie(fakeFavouriteMovieReq) } returns Result.Success(
                fakeInsertResponse
            )

            val insertFavouriteMovieResults = useCase.insertFavouriteMovie(fakeFavouriteMovieReq)
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

            coEvery { repository.insertFavouriteMovie(fakeFavouriteMovieReq) } returns Result.Error(
                Exception(failedResponse)
            )

            val insertFavouriteMovieResults = useCase.insertFavouriteMovie(fakeFavouriteMovieReq)
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

            coEvery { repository.deleteFavouriteMovie(fakeFavouriteMovieReq) } returns Result.Success(
                fakeSuccessResponse
            )

            val deleteFavouriteMovieResults = useCase.deleteFavouriteMovie(fakeFavouriteMovieReq)
            val result = deleteFavouriteMovieResults as Result.Success
            assertNotNull(deleteFavouriteMovieResults)
            assertEquals(fakeSuccessResponse, result.data)
        }
    }

    @Test
    fun `delete favourite movie failed`() {
        runBlockingTest {
            val fakeFavouriteMovieReq = RoomUtils.getFavouriteMovieReq()
            val fakeFailedResponse = RoomUtils.ERROR_DELETE_FROM_DB

            coEvery {
                repository.deleteFavouriteMovie(fakeFavouriteMovieReq)
            } returns Result.Error(Exception(fakeFailedResponse))

            val deleteFavouriteMovieResults = useCase.deleteFavouriteMovie(fakeFavouriteMovieReq)
            val result = deleteFavouriteMovieResults as Result.Error
            assertNotNull(deleteFavouriteMovieResults)
            assertEquals(fakeFailedResponse, result.error.message)
        }
    }

    @Test
    fun `get favourite movie found`() {
        runBlockingTest {
            val fakeFavouriteMoviesRes = RoomUtils.getFavouriteMoviesRes()

            coEvery { repository.getFavouriteMovies() } returns Result.Success(
                fakeFavouriteMoviesRes
            )

            val favouriteMoviesResult = useCase.getFavouriteMovies()
            val result = favouriteMoviesResult as Result.Success
            assertNotNull(favouriteMoviesResult)
            assertEquals(fakeFavouriteMoviesRes, result.data)
        }
    }

    @Test
    fun `get favourite movie not found`() {
        runBlockingTest {
            val fakeFailedResponse = RoomUtils.NO_DATA_FOUND

            coEvery { repository.getFavouriteMovies() } returns Result.Error(
                Exception(
                    fakeFailedResponse
                )
            )

            val favouriteMoviesResult = useCase.getFavouriteMovies()
            val result = favouriteMoviesResult as Result.Error
            assertNotNull(favouriteMoviesResult)
            assertEquals(fakeFailedResponse, result.error.message)
        }
    }

    @Test
    fun `get movies success`() {
        runBlockingTest {
            val fakeMoviesGenresRes = RoomUtils.getMoviesGenresRes()
            val fakeFavouriteMoviesRes = RoomUtils.getFavouriteMoviesRes()

            coEvery { repository.getFavouriteMovies() } returns Result.Success(
                fakeFavouriteMoviesRes
            )
            coEvery { repository.getMovies() } returns Result.Success(fakeMoviesGenresRes)

            val moviesResult = useCase.getMovies()
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
                repository.getFavouriteMovies()
            } returns Result.Error(Exception(fakeFailedResponse))

            val moviesResult = useCase.getMovies()
            val result = moviesResult as Result.Error
            assertNotNull(moviesResult)
            assertEquals(fakeFailedResponse, result.error.message)
        }
    }
}