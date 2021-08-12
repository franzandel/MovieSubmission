package com.franzandel.moviesubmission.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.franzandel.moviesubmission.data.local.dao.FavouriteMoviesDao
import com.franzandel.moviesubmission.data.local.entity.FavouriteMovieEntity

/**
 * Created by Franz Andel on 11/08/21.
 * Android Engineer
 */

@Database(
    entities = [FavouriteMovieEntity::class],
    version = 1,
    exportSchema = false
)
abstract class FavouriteMoviesDatabase : RoomDatabase() {
    abstract fun favouriteMoviesDao(): FavouriteMoviesDao
}