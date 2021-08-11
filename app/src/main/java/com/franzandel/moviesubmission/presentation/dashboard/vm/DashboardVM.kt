package com.franzandel.moviesubmission.presentation.dashboard.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.franzandel.moviesubmission.core.data.wrapper.Result
import com.franzandel.moviesubmission.core.external.coroutine.CoroutineThread
import com.franzandel.moviesubmission.core.presentation.vm.BaseViewModel
import com.franzandel.moviesubmission.domain.model.MovieRes
import com.franzandel.moviesubmission.domain.usecase.MoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Franz Andel on 11/08/21.
 * Android Engineer
 */

@HiltViewModel
class DashboardVM @Inject constructor(
    private val moviesUseCase: MoviesUseCase,
    private val coroutineThread: CoroutineThread
) : BaseViewModel() {

    private val _movies = MutableLiveData<List<MovieRes>?>()
    val movies: LiveData<List<MovieRes>?> = _movies

    fun getMovies() {
        viewModelScope.launch(coroutineThread.background()) {
            when (val result = moviesUseCase.getMovies()) {
                is Result.Success -> _movies.postValue(result.data)
                is Result.Error -> _error.postValue(result.error.localizedMessage)
            }
        }
    }
}