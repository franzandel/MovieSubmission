package com.franzandel.moviesubmission.presentation.popularmovies.vm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.franzandel.moviesubmission.core.data.wrapper.Result
import com.franzandel.moviesubmission.core.external.coroutine.CoroutineThread
import com.franzandel.moviesubmission.core.mapper.BaseMapper
import com.franzandel.moviesubmission.domain.model.FavouriteMovieReq
import com.franzandel.moviesubmission.domain.usecase.MoviesUseCase
import com.franzandel.moviesubmission.presentation.popularmovies.model.PopularMovieResUI
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
class PopularMoviesVMTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val useCase: MoviesUseCase = mockk(relaxed = true)
    private val coroutineThread: CoroutineThread = mockk(relaxed = true)
    private val mapper: BaseMapper<PopularMovieResUI, FavouriteMovieReq> = mockk(relaxed = true)
    private val successObserver: Observer<Pair<PopularMovieResUI, Int>> = mockk(relaxed = true)
    private val failedObserver: Observer<String> = mockk(relaxed = true)

    private lateinit var viewModel: PopularMoviesVM

    @Before
    fun setUp() {
        viewModel = PopularMoviesVM(useCase, coroutineThread, mapper)
    }

    @Test
    fun `insert popular movies success`() {
        runBlockingTest {
            val fakePopularMovieResUI = RoomUtils.getPopularMovieResUI()
            val fakeItemPosition = 3

            coEvery { mapper.map(fakePopularMovieResUI) } returns RoomUtils.getFavouriteMovieReq()
            val favouriteMovieReq = mapper.map(fakePopularMovieResUI)

            coEvery { useCase.insertFavouriteMovie(favouriteMovieReq) } returns Result.Success(Unit)
            coEvery { coroutineThread.background() } returns Dispatchers.Unconfined

            viewModel.insertFavouriteMovie.observeForever(successObserver)
            viewModel.insertFavouriteMovie(fakePopularMovieResUI, fakeItemPosition)

            verify {
                val insertFavouriteMovie = viewModel.insertFavouriteMovie.value
                successObserver.onChanged(insertFavouriteMovie)
                assertNotNull(insertFavouriteMovie)
                assertEquals(fakePopularMovieResUI, insertFavouriteMovie?.first)
                assertEquals(fakeItemPosition, insertFavouriteMovie?.second)
            }
        }
    }

    @Test
    fun `insert popular movies failed`() {
        runBlockingTest {
            val fakePopularMovieResUI = RoomUtils.getPopularMovieResUI()
            val fakeItemPosition = 3
            val fakeFailedResponse = RoomUtils.ERROR_INSERT_TO_DB

            coEvery { mapper.map(fakePopularMovieResUI) } returns RoomUtils.getFavouriteMovieReq()
            val favouriteMovieReq = mapper.map(fakePopularMovieResUI)

            coEvery {
                useCase.insertFavouriteMovie(favouriteMovieReq)
            } returns Result.Error(Exception(fakeFailedResponse))
            coEvery { coroutineThread.background() } returns Dispatchers.Unconfined

            viewModel.error.observeForever(failedObserver)
            viewModel.insertFavouriteMovie(fakePopularMovieResUI, fakeItemPosition)

            verify {
                val error = viewModel.error.value
                failedObserver.onChanged(error)
                assertNotNull(error)
                assertEquals(fakeFailedResponse, error)
            }
        }
    }

    @Test
    fun `delete popular movies success`() {
        runBlockingTest {
            val fakePopularMovieResUI = RoomUtils.getPopularMovieResUI()
            val fakeItemPosition = 3

            coEvery { mapper.map(fakePopularMovieResUI) } returns RoomUtils.getFavouriteMovieReq()
            val favouriteMovieReq = mapper.map(fakePopularMovieResUI)

            coEvery { useCase.deleteFavouriteMovie(favouriteMovieReq) } returns Result.Success(Unit)
            coEvery { coroutineThread.background() } returns Dispatchers.Unconfined

            viewModel.deleteFavouriteMovie.observeForever(successObserver)
            viewModel.deleteFavouriteMovie(fakePopularMovieResUI, fakeItemPosition)

            verify {
                val deleteFavouriteMovie = viewModel.deleteFavouriteMovie.value
                successObserver.onChanged(deleteFavouriteMovie)
                assertNotNull(deleteFavouriteMovie)
                assertEquals(fakePopularMovieResUI, deleteFavouriteMovie?.first)
                assertEquals(fakeItemPosition, deleteFavouriteMovie?.second)
            }
        }
    }

    @Test
    fun `delete popular movies failed`() {
        runBlockingTest {
            val popularMovieResUI = RoomUtils.getPopularMovieResUI()
            val fakeItemPosition = 3
            val fakeFailedResponse = RoomUtils.ERROR_DELETE_FROM_DB

            coEvery { mapper.map(popularMovieResUI) } returns RoomUtils.getFavouriteMovieReq()
            val favouriteMovieReq = mapper.map(popularMovieResUI)

            coEvery {
                useCase.deleteFavouriteMovie(favouriteMovieReq)
            } returns Result.Error(Exception(fakeFailedResponse))
            coEvery { coroutineThread.background() } returns Dispatchers.Unconfined

            viewModel.error.observeForever(failedObserver)
            viewModel.deleteFavouriteMovie(popularMovieResUI, fakeItemPosition)

            verify {
                val error = viewModel.error.value
                failedObserver.onChanged(error)
                assertNotNull(error)
                assertEquals(fakeFailedResponse, error)
            }
        }
    }
}