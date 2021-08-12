package com.franzandel.moviesubmission.di

import com.franzandel.moviesubmission.presentation.navigation.MoviesNavigation
import com.franzandel.moviesubmission.presentation.navigation.MoviesNavigationImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

/**
 * Created by Franz Andel on 12/08/21.
 * Android Engineer
 */

@Module
@InstallIn(ActivityComponent::class)
abstract class MoviesNavigationAbstractModule {
    @Binds
    @ActivityScoped
    abstract fun provideMoviesNavigation(moviesNavigationImpl: MoviesNavigationImpl): MoviesNavigation
}