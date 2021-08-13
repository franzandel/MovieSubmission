package com.franzandel.moviesubmission.di.toprated

import androidx.lifecycle.ViewModel
import com.franzandel.moviesubmission.core.mapper.BaseMapper
import com.franzandel.moviesubmission.core.presentation.vm.ViewModelKey
import com.franzandel.moviesubmission.domain.model.FavouriteMovieReq
import com.franzandel.moviesubmission.presentation.topratedmovies.mapper.FavouriteMovieReqMapper
import com.franzandel.moviesubmission.presentation.topratedmovies.model.TopRatedMovieResUI
import com.franzandel.moviesubmission.presentation.topratedmovies.vm.TopRatedMoviesVM
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.multibindings.IntoMap

/**
 * Created by Franz Andel on 13/08/21.
 * Android Engineer
 */

@Module
@InstallIn(ViewModelComponent::class)
abstract class TopRatedAbstractModule {

    @Binds
    @IntoMap
    @ViewModelKey(TopRatedMoviesVM::class)
    abstract fun provideTopRatedMoviesVM(topRatedMoviesVM: TopRatedMoviesVM): ViewModel

    @Binds
    @ViewModelScoped
    abstract fun provideFavouriteMovieReqMapper(favouriteMovieReqMapper: FavouriteMovieReqMapper): BaseMapper<TopRatedMovieResUI, FavouriteMovieReq>
}