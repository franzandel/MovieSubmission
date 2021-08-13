package com.franzandel.moviesubmission.di.favourite

import androidx.lifecycle.ViewModel
import com.franzandel.moviesubmission.core.presentation.vm.ViewModelKey
import com.franzandel.moviesubmission.presentation.favourite.vm.FavouriteMoviesVM
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
abstract class FavouriteMoviesAbstractModule {

    @Binds
    @IntoMap
    @ViewModelKey(FavouriteMoviesVM::class)
    abstract fun provideFavouriteMoviesVM(favouriteMoviesVM: FavouriteMoviesVM): ViewModel
}