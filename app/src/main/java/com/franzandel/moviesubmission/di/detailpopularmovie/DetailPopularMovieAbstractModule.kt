package com.franzandel.moviesubmission.di.detailpopularmovie

import androidx.lifecycle.ViewModel
import com.franzandel.moviesubmission.core.presentation.vm.ViewModelKey
import com.franzandel.moviesubmission.presentation.detailpopularmovie.vm.DetailPopularMovieVM
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
abstract class DetailPopularMovieAbstractModule {

    @Binds
    @IntoMap
    @ViewModelKey(DetailPopularMovieVM::class)
    abstract fun provideDetailPopularMovieVM(detailVM: DetailPopularMovieVM): ViewModel
}