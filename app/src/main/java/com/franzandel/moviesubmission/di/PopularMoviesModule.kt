package com.franzandel.moviesubmission.di

import androidx.lifecycle.ViewModel
import com.franzandel.moviesubmission.core.mapper.BaseMapper
import com.franzandel.moviesubmission.core.presentation.vm.ViewModelKey
import com.franzandel.moviesubmission.domain.model.FavouriteMovieReq
import com.franzandel.moviesubmission.presentation.popularmovies.mapper.MovieRequestMapper
import com.franzandel.moviesubmission.presentation.popularmovies.model.PopularMovieResUI
import com.franzandel.moviesubmission.presentation.popularmovies.vm.PopularMoviesVM
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.multibindings.IntoMap

/**
 * Created by Franz Andel on 12/08/21.
 * Android Engineer
 */

@Module
@InstallIn(ViewModelComponent::class)
abstract class PopularMoviesModule {

    @Binds
    @IntoMap
    @ViewModelKey(PopularMoviesVM::class)
    abstract fun providePopularMoviesVM(popularMoviesVM: PopularMoviesVM): ViewModel

    @Binds
    @ViewModelScoped
    abstract fun provideMovieRequestMapper(movieRequestMapper: MovieRequestMapper): BaseMapper<PopularMovieResUI, FavouriteMovieReq>
}