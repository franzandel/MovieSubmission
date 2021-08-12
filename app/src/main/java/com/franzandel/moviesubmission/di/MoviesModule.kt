package com.franzandel.moviesubmission.di

import com.franzandel.moviesubmission.core.mapper.BaseMapper
import com.franzandel.moviesubmission.core.mapper.RetrofitResMapper
import com.franzandel.moviesubmission.data.local.dao.FavouriteMoviesDao
import com.franzandel.moviesubmission.data.local.database.FavouriteMoviesDatabase
import com.franzandel.moviesubmission.data.local.entity.FavouriteMovieEntity
import com.franzandel.moviesubmission.data.local.mapper.FavouriteMoviesResponseMapper
import com.franzandel.moviesubmission.data.remote.mapper.RetrofitGenresResMapper
import com.franzandel.moviesubmission.data.remote.mapper.RetrofitMoviesResMapper
import com.franzandel.moviesubmission.data.remote.model.GenresResDTO
import com.franzandel.moviesubmission.data.remote.model.MoviesResDTO
import com.franzandel.moviesubmission.data.remote.network.MoviesNetworkService
import com.franzandel.moviesubmission.domain.model.FavouriteMovieRes
import com.franzandel.moviesubmission.domain.model.GenreRes
import com.franzandel.moviesubmission.domain.model.MovieGenreRes
import com.franzandel.moviesubmission.domain.model.MovieRes
import com.franzandel.moviesubmission.presentation.mapper.GenresResUIMapper
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
    fun provideRetrofitMoviesResMapper(gson: Gson): RetrofitResMapper<MoviesResDTO, List<MovieRes>> =
        RetrofitMoviesResMapper(gson)

    @Provides
    @ViewModelScoped
    fun provideRetrofitGenresResMapper(gson: Gson): RetrofitResMapper<GenresResDTO, List<GenreRes>> =
        RetrofitGenresResMapper(gson)

    @Provides
    @ViewModelScoped
    fun provideGenresResUIMapper(): BaseMapper<List<GenreRes>, String> = GenresResUIMapper()

    @Provides
    @ViewModelScoped
    fun providePopularMovieResUIMapper(
        genresResUIMapper: BaseMapper<List<GenreRes>, String>
    ): BaseMapper<List<MovieGenreRes>, List<PopularMovieResUI>> =
        PopularMovieResUIMapper(genresResUIMapper)

    @Provides
    @ViewModelScoped
    fun provideTopRatedMovieResUIMapper(
        genresResUIMapper: BaseMapper<List<GenreRes>, String>
    ): BaseMapper<List<MovieGenreRes>, List<TopRatedMovieResUI>> =
        TopRatedMovieResUIMapper(genresResUIMapper)

    @Provides
    @ViewModelScoped
    fun provideFavouriteMoviesResponseMapper(): BaseMapper<List<FavouriteMovieEntity>, List<FavouriteMovieRes>> =
        FavouriteMoviesResponseMapper()

    @Provides
    @ViewModelScoped
    fun provideFavouriteMoviesDao(database: FavouriteMoviesDatabase): FavouriteMoviesDao =
        database.favouriteMoviesDao()
}