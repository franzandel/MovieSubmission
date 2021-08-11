package com.franzandel.moviesubmission.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.franzandel.moviesubmission.data.local.dao.MoviesDao
import com.franzandel.moviesubmission.data.local.entity.MovieEntity

/**
 * Created by Franz Andel on 11/08/21.
 * Android Engineer
 */

@Database(
    entities = [MovieEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
}