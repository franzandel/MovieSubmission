package com.franzandel.moviesubmission.data.local.dao

import androidx.room.*
import com.franzandel.moviesubmission.data.local.entity.FavouriteMovieEntity

/**
 * Created by Franz Andel on 11/08/21.
 * Android Engineer
 */

@Dao
interface FavouriteMoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavouriteMovie(movieEntity: FavouriteMovieEntity): Long

    @Delete
    suspend fun deleteFavouriteMovie(movieEntity: FavouriteMovieEntity): Int

    @Query("SELECT * FROM tbl_favourite_movie")
    suspend fun getFavouriteMovies(): List<FavouriteMovieEntity>
}