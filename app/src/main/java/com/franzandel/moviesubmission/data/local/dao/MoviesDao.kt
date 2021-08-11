package com.franzandel.moviesubmission.data.local.dao

import androidx.room.*
import com.franzandel.moviesubmission.data.local.entity.MovieEntity

/**
 * Created by Franz Andel on 11/08/21.
 * Android Engineer
 */

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieEntity(movieEntity: MovieEntity): Long

    @Delete
    suspend fun deleteMovieEntity(movieEntity: MovieEntity): Int

    @Query("SELECT * FROM tbl_movie")
    suspend fun getMovieEntities(): List<MovieEntity>

    @Query("SELECT * FROM tbl_movie WHERE id = :id")
    suspend fun getMovieEntity(id: Int): MovieEntity?
}