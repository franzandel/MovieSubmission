package com.franzandel.moviesubmission.di.detailfavouritemovie

import androidx.lifecycle.ViewModel
import com.franzandel.moviesubmission.core.mapper.BaseMapper
import com.franzandel.moviesubmission.core.presentation.vm.ViewModelKey
import com.franzandel.moviesubmission.domain.model.FavouriteMovieReq
import com.franzandel.moviesubmission.presentation.detailfavouritemovie.mapper.FavouriteMovieReqMapper
import com.franzandel.moviesubmission.presentation.detailfavouritemovie.vm.DetailFavouriteMovieVM
import com.franzandel.moviesubmission.presentation.favouritemovies.model.FavouriteMovieResUI
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
abstract class DetailFavouriteMovieAbstractModule {

    @Binds
    @IntoMap
    @ViewModelKey(DetailFavouriteMovieVM::class)
    abstract fun provideDetailFavouriteMovieVM(detailFavouriteMovieVM: DetailFavouriteMovieVM): ViewModel

    @Binds
    @ViewModelScoped
    abstract fun provideFavouriteMovieReqMapper(favouriteMovieReqMapper: FavouriteMovieReqMapper): BaseMapper<FavouriteMovieResUI, FavouriteMovieReq>
}