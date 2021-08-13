package com.franzandel.moviesubmission.di.movies

import com.franzandel.moviesubmission.core.mapper.BaseMapper
import com.franzandel.moviesubmission.data.local.MoviesLocalData
import com.franzandel.moviesubmission.data.local.MoviesLocalDataImpl
import com.franzandel.moviesubmission.data.local.entity.FavouriteMovieEntity
import com.franzandel.moviesubmission.data.local.mapper.FavouriteMovieEntityMapper
import com.franzandel.moviesubmission.data.local.mapper.FavouriteMovieResponseMapper
import com.franzandel.moviesubmission.data.remote.MoviesRemoteData
import com.franzandel.moviesubmission.data.remote.MoviesRemoteDataImpl
import com.franzandel.moviesubmission.data.repository.MoviesRepositoryImpl
import com.franzandel.moviesubmission.domain.model.FavouriteMovieReq
import com.franzandel.moviesubmission.domain.model.FavouriteMovieRes
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

    @Binds
    @ViewModelScoped
    abstract fun provideFavouriteMovieEntityMapper(movieEntityMapper: FavouriteMovieEntityMapper): BaseMapper<FavouriteMovieReq, FavouriteMovieEntity>

    @Binds
    @ViewModelScoped
    abstract fun provideFavouriteMovieResponseMapper(movieRequestMapper: FavouriteMovieResponseMapper): BaseMapper<FavouriteMovieEntity, FavouriteMovieRes>

    @Binds
    @ViewModelScoped
    abstract fun provideMoviesLocalData(moviesLocalDataImpl: MoviesLocalDataImpl): MoviesLocalData
}