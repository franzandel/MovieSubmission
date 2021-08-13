package com.franzandel.moviesubmission.di.favourite

import com.franzandel.moviesubmission.core.mapper.BaseMapper
import com.franzandel.moviesubmission.domain.model.FavouriteMovieRes
import com.franzandel.moviesubmission.presentation.favourite.mapper.FavouriteMovieResUIMapper
import com.franzandel.moviesubmission.presentation.favourite.model.FavouriteMovieResUI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

/**
 * Created by Franz Andel on 13/08/21.
 * Android Engineer
 */

@Module
@InstallIn(ViewModelComponent::class)
object FavouriteMoviesModule {

    @Provides
    @ViewModelScoped
    fun providePopularMovieResUIMapper(): BaseMapper<List<FavouriteMovieRes>, List<FavouriteMovieResUI>> =
        FavouriteMovieResUIMapper()
}