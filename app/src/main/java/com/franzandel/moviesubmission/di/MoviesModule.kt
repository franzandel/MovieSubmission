package com.franzandel.moviesubmission.di

import com.franzandel.moviesubmission.core.mapper.BaseMapper
import com.franzandel.moviesubmission.core.mapper.RetrofitResMapper
import com.franzandel.moviesubmission.data.remote.mapper.MovieMapper
import com.franzandel.moviesubmission.data.remote.model.MoviesResDTO
import com.franzandel.moviesubmission.data.remote.network.MoviesNetworkService
import com.franzandel.moviesubmission.domain.model.MovieRes
import com.franzandel.moviesubmission.presentation.popularmovies.mapper.PopularMovieResUIMapper
import com.franzandel.moviesubmission.presentation.popularmovies.model.PopularMovieResUI
import com.franzandel.moviesubmission.presentation.topratedmovies.mapper.TopRatedMovieResUIMapper
import com.franzandel.moviesubmission.presentation.topratedmovies.model.TopRatedMovieResUI
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit

/**
 * Created by Franz Andel on 11/08/21.
 * Android Engineer
 */

@Module
@InstallIn(ViewModelComponent::class)
object MoviesModule {

    @Provides
    @ViewModelScoped
    fun provideMoviesNetworkService(retrofit: Retrofit): MoviesNetworkService =
        retrofit.create(MoviesNetworkService::class.java)

    @Provides
    @ViewModelScoped
    fun provideGamesResultsUIMapper(gson: Gson): RetrofitResMapper<MoviesResDTO, List<MovieRes>> =
        MovieMapper(gson)

    @Provides
    @ViewModelScoped
    fun providePopularMovieResUIMapper(): BaseMapper<List<MovieRes>, List<PopularMovieResUI>> =
        PopularMovieResUIMapper()

    @Provides
    @ViewModelScoped
    fun provideTopRatedMovieResUIMapper(): BaseMapper<List<MovieRes>, List<TopRatedMovieResUI>> =
        TopRatedMovieResUIMapper()
}