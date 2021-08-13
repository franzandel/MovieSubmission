package com.franzandel.moviesubmission.di.detailtopratedmovie

import androidx.lifecycle.ViewModel
import com.franzandel.moviesubmission.core.presentation.vm.ViewModelKey
import com.franzandel.moviesubmission.presentation.detailtopratedmovie.vm.DetailTopRatedMovieVM
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.multibindings.IntoMap

/**
 * Created by Franz Andel on 13/08/21.
 * Android Engineer
 */

@Module
@InstallIn(ViewModelComponent::class)
abstract class DetailTopRatedMovieAbstractModule {

    @Binds
    @IntoMap
    @ViewModelKey(DetailTopRatedMovieVM::class)
    abstract fun provideDetailTopRatedMovieVM(detailTopRatedMovieVM: DetailTopRatedMovieVM): ViewModel
}