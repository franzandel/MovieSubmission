package com.franzandel.moviesubmission.presentation.dashboard.vm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.franzandel.moviesubmission.core.data.wrapper.Result
import com.franzandel.moviesubmission.core.external.coroutine.CoroutineThread
import com.franzandel.moviesubmission.core.mapper.BaseMapper
import com.franzandel.moviesubmission.domain.model.FavouriteMovieRes
import com.franzandel.moviesubmission.domain.model.MovieGenreRes
import com.franzandel.moviesubmission.domain.usecase.MoviesUseCase
import com.franzandel.moviesubmission.presentation.favouritemovies.model.FavouriteMovieResUI
import com.franzandel.moviesubmission.presentation.popularmovies.model.PopularMovieResUI
import com.franzandel.moviesubmission.presentation.topratedmovies.model.TopRatedMovieResUI
import com.franzandel.moviesubmission.utils.RetrofitUtils
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
class DashboardVMTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val useCase: MoviesUseCase = mockk(relaxed = true)
    private val coroutineThread: CoroutineThread = mockk(relaxed = true)
    private val popularMovieResUIMapper: BaseMapper<List<MovieGenreRes>, List<PopularMovieResUI>> =
        mockk(relaxed = true)
    private val topRatedMovieResUIMapper: BaseMapper<List<MovieGenreRes>, List<TopRatedMovieResUI>> =
        mockk(relaxed = true)
    private val favouriteMovieResUIMapper: BaseMapper<List<FavouriteMovieRes>, List<FavouriteMovieResUI>> =
        mockk(relaxed = true)
    private val getPopularMoviesResUISuccessObserver: Observer<List<PopularMovieResUI>> =
        mockk(relaxed = true)
    private val getTopRatedMovieResUISuccessObserver: Observer<List<TopRatedMovieResUI>> =
        mockk(relaxed = true)
    private val getFavouriteMovieResUISuccessObserver: Observer<List<FavouriteMovieResUI>> =
        mockk(relaxed = true)
    private val failedObserver: Observer<String> = mockk(relaxed = true)

    private lateinit var viewModel: DashboardVM

    @Before
    fun setUp() {
        viewModel = DashboardVM(
            useCase,
            coroutineThread,
            popularMovieResUIMapper,
            topRatedMovieResUIMapper,
            favouriteMovieResUIMapper
        )
    }

    @Test
    fun `get movies success`() {
        runBlockingTest {
            val moviesGenresRes = RetrofitUtils.getMoviesGenresRes()

            coEvery { popularMovieResUIMapper.map(moviesGenresRes) } returns RoomUtils.getPopularMoviesResUI()
            val popularMoviesResUI = popularMovieResUIMapper.map(moviesGenresRes)

            coEvery { topRatedMovieResUIMapper.map(moviesGenresRes) } returns RoomUtils.getTopRatedMoviesResUI()
            val topRatedMovieResUI = topRatedMovieResUIMapper.map(moviesGenresRes)

            coEvery { useCase.getMovies() } returns Result.Success(moviesGenresRes)
            coEvery { coroutineThread.background() } returns Dispatchers.Unconfined

            viewModel.popularMovies.observeForever(getPopularMoviesResUISuccessObserver)
            viewModel.topRatedMovies.observeForever(getTopRatedMovieResUISuccessObserver)
            viewModel.getMovies()

            verify {
                val popularMoviesResUIResult = viewModel.popularMovies.value
                getPopularMoviesResUISuccessObserver.onChanged(popularMoviesResUIResult)
                assertNotNull(popularMoviesResUIResult)
                assertEquals(popularMoviesResUI, popularMoviesResUIResult)

                val topRatedMoviesResUIResult = viewModel.topRatedMovies.value
                getTopRatedMovieResUISuccessObserver.onChanged(topRatedMoviesResUIResult)
                assertNotNull(topRatedMoviesResUIResult)
                assertEquals(topRatedMovieResUI, topRatedMoviesResUIResult)
            }
        }
    }

    @Test
    fun `get movies failed`() {
        runBlockingTest {
            val fakeFailedResponse = RetrofitUtils.GET_DATA_FAILED

            coEvery {
                useCase.getMovies()
            } returns Result.Error(Exception(fakeFailedResponse))
            coEvery { coroutineThread.background() } returns Dispatchers.Unconfined

            viewModel.error.observeForever(failedObserver)
            viewModel.getMovies()

            verify {
                val error = viewModel.error.value
                failedObserver.onChanged(error)
                assertNotNull(error)
                assertEquals(fakeFailedResponse, error)
            }
        }
    }

    @Test
    fun `get favourite movies found`() {
        runBlockingTest {
            val favouriteMoviesRes = RoomUtils.getFavouriteMoviesRes()

            coEvery { favouriteMovieResUIMapper.map(favouriteMoviesRes) } returns RoomUtils.getFavouriteMoviesResUI()
            val favouriteMoviesResUI = favouriteMovieResUIMapper.map(favouriteMoviesRes)

            coEvery { useCase.getFavouriteMovies() } returns Result.Success(favouriteMoviesRes)
            coEvery { coroutineThread.background() } returns Dispatchers.Unconfined

            viewModel.favouriteMovies.observeForever(getFavouriteMovieResUISuccessObserver)
            viewModel.getFavouriteMovies()

            verify {
                val favouriteMovies = viewModel.favouriteMovies.value
                getFavouriteMovieResUISuccessObserver.onChanged(favouriteMovies)
                assertNotNull(favouriteMovies)
                assertEquals(favouriteMoviesResUI, favouriteMovies)
            }
        }
    }

    @Test
    fun `get favourite movies not found`() {
        runBlockingTest {
            val fakeNotFoundResponse = RoomUtils.NO_DATA_FOUND

            coEvery {
                useCase.getFavouriteMovies()
            } returns Result.Error(Exception(fakeNotFoundResponse))
            coEvery { coroutineThread.background() } returns Dispatchers.Unconfined

            viewModel.error.observeForever(failedObserver)
            viewModel.getFavouriteMovies()

            verify {
                val error = viewModel.error.value
                failedObserver.onChanged(error)
                assertNotNull(error)
                assertEquals(fakeNotFoundResponse, error)
            }
        }
    }

    @Test
    fun `update popular movies found`() {
        `get movies success`()

        runBlockingTest {
            val fakeId = 1
            val fakeIsFavourite = true
            val moviesGenresRes = RetrofitUtils.getMoviesGenresRes()

            val popularMoviesResUI = popularMovieResUIMapper.map(moviesGenresRes)
            popularMoviesResUI.find {
                it.id == fakeId
            }?.isFavourite = fakeIsFavourite

            coEvery { coroutineThread.default() } returns Dispatchers.Unconfined

            viewModel.popularMovies.observeForever(getPopularMoviesResUISuccessObserver)
            viewModel.updatePopularMovies(fakeId, fakeIsFavourite)

            verify {
                val popularMoviesResUIResult = viewModel.popularMovies.value
                getPopularMoviesResUISuccessObserver.onChanged(popularMoviesResUIResult)
                assertNotNull(popularMoviesResUIResult)

                val updatedPopularMovieResUIResult = popularMoviesResUIResult?.find {
                    it.id == fakeId
                }
                val fakePopularMovieResUI = popularMoviesResUIResult?.find {
                    it.id == fakeId
                }
                assertEquals(
                    fakePopularMovieResUI?.isFavourite,
                    updatedPopularMovieResUIResult?.isFavourite
                )
            }
        }
    }

    @Test
    fun `update popular movies not found`() {
        `get movies success`()

        runBlockingTest {
            val fakeId = 3
            val fakeIsFavourite = true
            val moviesGenresRes = RetrofitUtils.getMoviesGenresRes()

            val popularMoviesResUI = popularMovieResUIMapper.map(moviesGenresRes)
            popularMoviesResUI.find {
                it.id == fakeId
            }?.isFavourite = fakeIsFavourite

            coEvery { coroutineThread.default() } returns Dispatchers.Unconfined

            viewModel.popularMovies.observeForever(getPopularMoviesResUISuccessObserver)
            viewModel.updatePopularMovies(fakeId, fakeIsFavourite)

            verify {
                val popularMoviesResUIResult = viewModel.popularMovies.value
                getPopularMoviesResUISuccessObserver.onChanged(popularMoviesResUIResult)
                assertNotNull(popularMoviesResUIResult)

                val updatedPopularMovieResUIResult = popularMoviesResUIResult?.find {
                    it.id == fakeId
                }
                val fakePopularMovieResUI = popularMoviesResUIResult?.find {
                    it.id == fakeId
                }
                assertEquals(
                    fakePopularMovieResUI?.isFavourite,
                    updatedPopularMovieResUIResult?.isFavourite
                )
            }
        }
    }

    @Test
    fun `update top rated movies found`() {
        `get movies success`()

        runBlockingTest {
            val fakeId = 1
            val fakeIsFavourite = true
            val moviesGenresRes = RetrofitUtils.getMoviesGenresRes()

            val topRatedMoviesResUI = topRatedMovieResUIMapper.map(moviesGenresRes)
            topRatedMoviesResUI.find {
                it.id == fakeId
            }?.isFavourite = fakeIsFavourite

            coEvery { coroutineThread.default() } returns Dispatchers.Unconfined

            viewModel.topRatedMovies.observeForever(getTopRatedMovieResUISuccessObserver)
            viewModel.updateTopRatedMovies(fakeId, fakeIsFavourite)

            verify {
                val topRatedMoviesResUIResult = viewModel.popularMovies.value
                getPopularMoviesResUISuccessObserver.onChanged(topRatedMoviesResUIResult)
                assertNotNull(topRatedMoviesResUIResult)

                val updatedTopRatedMovieResUIResult = topRatedMoviesResUIResult?.find {
                    it.id == fakeId
                }
                val fakeTopRatedMovieResUI = topRatedMoviesResUIResult?.find {
                    it.id == fakeId
                }
                assertEquals(
                    fakeTopRatedMovieResUI?.isFavourite,
                    updatedTopRatedMovieResUIResult?.isFavourite
                )
            }
        }
    }

    @Test
    fun `update top rated movies not found`() {
        `get movies success`()

        runBlockingTest {
            val fakeId = 3
            val fakeIsFavourite = true
            val moviesGenresRes = RetrofitUtils.getMoviesGenresRes()

            val topRatedMoviesResUI = topRatedMovieResUIMapper.map(moviesGenresRes)
            topRatedMoviesResUI.find {
                it.id == fakeId
            }?.isFavourite = fakeIsFavourite

            coEvery { coroutineThread.default() } returns Dispatchers.Unconfined

            viewModel.topRatedMovies.observeForever(getTopRatedMovieResUISuccessObserver)
            viewModel.updateTopRatedMovies(fakeId, fakeIsFavourite)

            verify {
                val topRatedMoviesResUIResult = viewModel.popularMovies.value
                getPopularMoviesResUISuccessObserver.onChanged(topRatedMoviesResUIResult)
                assertNotNull(topRatedMoviesResUIResult)

                val updatedTopRatedMovieResUIResult = topRatedMoviesResUIResult?.find {
                    it.id == fakeId
                }
                val fakeTopRatedMovieResUI = topRatedMoviesResUIResult?.find {
                    it.id == fakeId
                }
                assertEquals(
                    fakeTopRatedMovieResUI?.isFavourite,
                    updatedTopRatedMovieResUIResult?.isFavourite
                )
            }
        }
    }
}