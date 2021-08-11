package com.franzandel.moviesubmission.di

import com.franzandel.moviesubmission.data.remote.MoviesRemoteData
import com.franzandel.moviesubmission.data.remote.MoviesRemoteDataImpl
import com.franzandel.moviesubmission.data.repository.MoviesRepositoryImpl
import com.franzandel.moviesubmission.domain.repository.MoviesRepository
import com.franzandel.moviesubmission.domain.usecase.MoviesUseCase
import com.franzandel.moviesubmission.domain.usecase.MoviesUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

/**
 * Created by Franz Andel on 11/08/21.
 * Android Engineer
 */

@Module
@InstallIn(ViewModelComponent::class)
abstract class MoviesAbstractModule {

    @Binds
    @ViewModelScoped
    abstract fun provideMoviesUseCase(moviesUseCaseImpl: MoviesUseCaseImpl): MoviesUseCase

    @Binds
    @ViewModelScoped
    abstract fun provideMoviesRepository(moviesRepositoryImpl: MoviesRepositoryImpl): MoviesRepository

    @Binds
    @ViewModelScoped
    abstract fun provideMoviesRemoteData(moviesRemoteDataImpl: MoviesRemoteDataImpl): MoviesRemoteData
}