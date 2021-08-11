package com.franzandel.moviesubmission.presentation.dashboard.vm

import androidx.lifecycle.ViewModel
import com.franzandel.moviesubmission.domain.usecase.MoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Franz Andel on 11/08/21.
 * Android Engineer
 */

@HiltViewModel
class DashboardVM @Inject constructor(private val moviesUseCase: MoviesUseCase) : ViewModel() {

}