package com.franzandel.moviesubmission.data.local.dao

import androidx.room.*
import com.franzandel.moviesubmission.data.local.entity.MovieEntity

/**
 * Created by Franz Andel on 11/08/21.
 * Android Engineer
 */

@Dao
interface FavouriteMoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavouriteMovie(movieEntity: MovieEntity): Long

    @Delete
    suspend fun deleteFavouriteMovie(movieEntity: MovieEntity): Int

    @Query("SELECT * FROM tbl_movie")
    suspend fun getFavouriteMovies(): List<MovieEntity>

    @Query("SELECT * FROM tbl_movie WHERE id = :id")
    suspend fun getFavouriteMovie(id: Int): MovieEntity?
}