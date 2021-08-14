package com.franzandel.moviesubmission.data.local

import com.franzandel.moviesubmission.core.data.wrapper.Result
import com.franzandel.moviesubmission.core.mapper.BaseMapper
import com.franzandel.moviesubmission.data.local.dao.FavouriteMoviesDao
import com.franzandel.moviesubmission.data.local.entity.FavouriteMovieEntity
import com.franzandel.moviesubmission.domain.model.FavouriteMovieReq
import com.franzandel.moviesubmission.domain.model.FavouriteMovieRes
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
class MoviesLocalDataImplTest {

    private val dao: FavouriteMoviesDao = mockk(relaxed = true)
    private val requestMapper: BaseMapper<FavouriteMovieReq, FavouriteMovieEntity> =
        mockk(relaxed = true)
    private val responsesMapper: BaseMapper<List<FavouriteMovieEntity>, List<FavouriteMovieRes>> =
        mockk(relaxed = true)

    private lateinit var localData: MoviesLocalData

    @Before
    fun setUp() {
        localData = MoviesLocalDataImpl(dao, requestMapper, responsesMapper)
    }

    @Test
    fun `insert favourite movie success`() {
        runBlockingTest {
            val fakeFavouriteMovieReq = RoomUtils.getFavouriteMovieReq()
            val fakeInsertResponse = 3L
            val fakeSuccessResponse = Unit

            coEvery { requestMapper.map(fakeFavouriteMovieReq) } returns RoomUtils.getFavouriteMovieEntity()
            val fakeFavouriteMovieEntity = requestMapper.map(fakeFavouriteMovieReq)

            coEvery { dao.insertFavouriteMovie(fakeFavouriteMovieEntity) } returns fakeInsertResponse

            val insertFavouriteMovieResults = localData.insertFavouriteMovie(fakeFavouriteMovieReq)
            val result = insertFavouriteMovieResults as Result.Success
            assertNotNull(insertFavouriteMovieResults)
            assertEquals(fakeSuccessResponse, result.data)
        }
    }

    @Test
    fun `insert favourite movie failed`() {
        runBlockingTest {
            val fakeFavouriteMovieReq = RoomUtils.getFavouriteMovieReq()
            val fakeInsertResponse = -1L
            val fakeErrorResponse = RoomUtils.ERROR_INSERT_TO_DB

            coEvery { requestMapper.map(fakeFavouriteMovieReq) } returns RoomUtils.getFavouriteMovieEntity()
            val fakeFavouriteMovieEntity = requestMapper.map(fakeFavouriteMovieReq)

            coEvery { dao.insertFavouriteMovie(fakeFavouriteMovieEntity) } returns fakeInsertResponse

            val insertFavouriteMovieResults = localData.insertFavouriteMovie(fakeFavouriteMovieReq)
            val result = insertFavouriteMovieResults as Result.Error
            assertNotNull(insertFavouriteMovieResults)
            assertEquals(fakeErrorResponse, result.error.message)
        }
    }

    @Test
    fun `delete favourite movie success`() {
        runBlockingTest {
            val fakeFavouriteMovieReq = RoomUtils.getFavouriteMovieReq()
            val fakeDeleteResponse = 3
            val fakeSuccessResponse = Unit

            coEvery { requestMapper.map(fakeFavouriteMovieReq) } returns RoomUtils.getFavouriteMovieEntity()
            val fakeFavouriteMovieEntity = requestMapper.map(fakeFavouriteMovieReq)

            coEvery { dao.deleteFavouriteMovie(fakeFavouriteMovieEntity) } returns fakeDeleteResponse

            val deleteFavouriteMovieResults = localData.deleteFavouriteMovie(fakeFavouriteMovieReq)
            val result = deleteFavouriteMovieResults as Result.Success
            assertNotNull(deleteFavouriteMovieResults)
            assertEquals(fakeSuccessResponse, result.data)
        }
    }

    @Test
    fun `delete favourite movie failed`() {
        runBlockingTest {
            val fakeFavouriteMovieReq = RoomUtils.getFavouriteMovieReq()
            val fakeDeleteResponse = -1
            val fakeErrorResponse = RoomUtils.ERROR_DELETE_FROM_DB

            coEvery { requestMapper.map(fakeFavouriteMovieReq) } returns RoomUtils.getFavouriteMovieEntity()
            val fakeFavouriteMovieEntity = requestMapper.map(fakeFavouriteMovieReq)

            coEvery {
                dao.deleteFavouriteMovie(fakeFavouriteMovieEntity)
            } returns fakeDeleteResponse

            val deleteFavouriteMovieResults = localData.deleteFavouriteMovie(fakeFavouriteMovieReq)
            val result = deleteFavouriteMovieResults as Result.Error
            assertNotNull(deleteFavouriteMovieResults)
            assertEquals(fakeErrorResponse, result.error.message)
        }
    }

    @Test
    fun `get favourite movies found`() {
        runBlockingTest {
            val fakeFavouriteMoviesEntity = RoomUtils.getFavouriteMoviesEntity()
            val fakeFavouriteMoviesRes = RoomUtils.getFavouriteMoviesRes()

            coEvery { responsesMapper.map(fakeFavouriteMoviesEntity) } returns fakeFavouriteMoviesRes
            coEvery { dao.getFavouriteMovies() } returns fakeFavouriteMoviesEntity

            val favouriteMoviesResults = localData.getFavouriteMovies()
            val result = favouriteMoviesResults as Result.Success
            assertNotNull(favouriteMoviesResults)
            assertEquals(fakeFavouriteMoviesRes, result.data)
        }
    }

    @Test
    fun `get favourite movies not found`() {
        runBlockingTest {
            val fakeFavouriteMoviesEntity = listOf<FavouriteMovieEntity>()

            coEvery { responsesMapper.map(fakeFavouriteMoviesEntity) } returns listOf()
            coEvery { dao.getFavouriteMovies() } returns fakeFavouriteMoviesEntity

            val favouriteMoviesResults = localData.getFavouriteMovies()
            val result = favouriteMoviesResults as Result.Success
            assertNotNull(favouriteMoviesResults)
            assertEquals(fakeFavouriteMoviesEntity.size, result.data.size)
        }
    }
}