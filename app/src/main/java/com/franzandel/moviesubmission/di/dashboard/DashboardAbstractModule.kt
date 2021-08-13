package com.franzandel.moviesubmission.di.dashboard

import androidx.lifecycle.ViewModel
import com.franzandel.moviesubmission.core.presentation.vm.ViewModelKey
import com.franzandel.moviesubmission.presentation.dashboard.vm.DashboardVM
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.multibindings.IntoMap

/**
 * Created by Franz Andel on 11/08/21.
 * Android Engineer
 */

@Module
@InstallIn(ViewModelComponent::class)
abstract class DashboardAbstractModule {

    @Binds
    @IntoMap
    @ViewModelKey(DashboardVM::class)
    abstract fun provideDashboardVM(dashboardVM: DashboardVM): ViewModel
}