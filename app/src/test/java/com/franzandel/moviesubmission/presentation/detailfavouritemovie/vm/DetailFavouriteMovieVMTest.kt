package com.franzandel.moviesubmission.presentation.detailfavouritemovie.vm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.franzandel.moviesubmission.core.data.wrapper.Result
import com.franzandel.moviesubmission.core.external.coroutine.CoroutineThread
import com.franzandel.moviesubmission.core.mapper.BaseMapper
import com.franzandel.moviesubmission.domain.model.FavouriteMovieReq
import com.franzandel.moviesubmission.domain.usecase.MoviesUseCase
import com.franzandel.moviesubmission.presentation.favouritemovies.model.FavouriteMovieResUI
import com.franzandel.moviesubmission.utils.RoomUtils
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by Franz Andel on 14/08/21.
 * Android Engineer
 */

@ExperimentalCoroutinesApi
class DetailFavouriteMovieVMTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val useCase: MoviesUseCase = mockk(relaxed = true)
    private val coroutineThread: CoroutineThread = mockk(relaxed = true)
    private val mapper: BaseMapper<FavouriteMovieResUI, FavouriteMovieReq> = mockk(relaxed = true)
    private val successObserver: Observer<Unit> = mockk(relaxed = true)
    private val failedObserver: Observer<String> = mockk(relaxed = true)

    private lateinit var viewModel: DetailFavouriteMovieVM

    @Before
    fun setUp() {
        viewModel = DetailFavouriteMovieVM(useCase, coroutineThread, mapper)
    }

    @Test
    fun `insert favourite movie success`() {
        runBlockingTest {
            val favouriteMovieResUI = RoomUtils.getFavouriteMovieResUI()
            val fakeSuccessResponse = Unit

            coEvery { mapper.map(favouriteMovieResUI) } returns RoomUtils.getFavouriteMovieReq()
            val favouriteMovieReq = mapper.map(favouriteMovieResUI)

            coEvery { useCase.insertFavouriteMovie(favouriteMovieReq) } returns Result.Success(Unit)
            coEvery { coroutineThread.background() } returns Dispatchers.Unconfined

            viewModel.insertFavouriteMovie.observeForever(successObserver)
            viewModel.insertFavouriteMovie(favouriteMovieResUI)

            verify {
                val insertFavouriteMovie = viewModel.insertFavouriteMovie.value
                successObserver.onChanged(insertFavouriteMovie)
                assertNotNull(insertFavouriteMovie)
                assertEquals(fakeSuccessResponse, insertFavouriteMovie)
            }
        }
    }

    @Test
    fun `insert favourite movie failed`() {
        runBlockingTest {
            val favouriteMovieResUI = RoomUtils.getFavouriteMovieResUI()
            val fakeFailedResponse = RoomUtils.ERROR_INSERT_TO_DB

            coEvery { mapper.map(favouriteMovieResUI) } returns RoomUtils.getFavouriteMovieReq()
            val favouriteMovieReq = mapper.map(favouriteMovieResUI)

            coEvery {
                useCase.insertFavouriteMovie(favouriteMovieReq)
            } returns Result.Error(Exception(fakeFailedResponse))
            coEvery { coroutineThread.background() } returns Dispatchers.Unconfined

            viewModel.error.observeForever(failedObserver)
            viewModel.insertFavouriteMovie(favouriteMovieResUI)

            verify {
                val error = viewModel.error.value
                failedObserver.onChanged(error)
                assertNotNull(error)
                assertEquals(fakeFailedResponse, error)
            }
        }
    }

    @Test
    fun `delete favourite movie success`() {
        runBlockingTest {
            val favouriteMovieResUI = RoomUtils.getFavouriteMovieResUI()
            val fakeSuccessResponse = Unit

            coEvery { mapper.map(favouriteMovieResUI) } returns RoomUtils.getFavouriteMovieReq()
            val favouriteMovieReq = mapper.map(favouriteMovieResUI)

            coEvery { useCase.deleteFavouriteMovie(favouriteMovieReq) } returns Result.Success(Unit)
            coEvery { coroutineThread.background() } returns Dispatchers.Unconfined

            viewModel.deleteFavouriteMovie.observeForever(successObserver)
            viewModel.deleteFavouriteMovie(favouriteMovieResUI)

            verify {
                val deleteFavouriteMovie = viewModel.deleteFavouriteMovie.value
                successObserver.onChanged(deleteFavouriteMovie)
                assertNotNull(deleteFavouriteMovie)
                assertEquals(fakeSuccessResponse, deleteFavouriteMovie)
            }
        }
    }

    @Test
    fun `delete favourite movie failed`() {
        runBlockingTest {
            val favouriteMovieResUI = RoomUtils.getFavouriteMovieResUI()
            val fakeFailedResponse = RoomUtils.ERROR_DELETE_FROM_DB

            coEvery { mapper.map(favouriteMovieResUI) } returns RoomUtils.getFavouriteMovieReq()
            val favouriteMovieReq = mapper.map(favouriteMovieResUI)

            coEvery {
                useCase.deleteFavouriteMovie(favouriteMovieReq)
            } returns Result.Error(Exception(fakeFailedResponse))
            coEvery { coroutineThread.background() } returns Dispatchers.Unconfined

            viewModel.error.observeForever(failedObserver)
            viewModel.deleteFavouriteMovie(favouriteMovieResUI)

            verify {
                val error = viewModel.error.value
                failedObserver.onChanged(error)
                assertNotNull(error)
                assertEquals(fakeFailedResponse, error)
            }
        }
    }
}