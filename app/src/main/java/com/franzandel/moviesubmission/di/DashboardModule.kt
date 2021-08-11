package com.franzandel.moviesubmission.di

import androidx.lifecycle.ViewModel
import com.franzandel.moviesubmission.core.external.coroutine.CoroutineThread
import com.franzandel.moviesubmission.core.presentation.vm.ViewModelKey
import com.franzandel.moviesubmission.domain.usecase.MoviesUseCase
import com.franzandel.moviesubmission.presentation.dashboard.vm.DashboardVM
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.multibindings.IntoMap

/**
 * Created by Franz Andel on 11/08/21.
 * Android Engineer
 */

@Module
@InstallIn(ViewModelComponent::class)
object DashboardModule {

    @Provides
    @IntoMap
    @ViewModelKey(DashboardVM::class)
    fun provideDashboardVM(useCase: MoviesUseCase, coroutineThread: CoroutineThread): ViewModel =
        DashboardVM(useCase, coroutineThread)
}