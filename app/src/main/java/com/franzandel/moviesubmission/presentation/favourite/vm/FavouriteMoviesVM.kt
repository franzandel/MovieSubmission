package com.franzandel.moviesubmission.presentation.favourite.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.franzandel.moviesubmission.core.data.wrapper.Result
import com.franzandel.moviesubmission.core.external.coroutine.CoroutineThread
import com.franzandel.moviesubmission.core.mapper.BaseMapper
import com.franzandel.moviesubmission.core.presentation.vm.BaseViewModel
import com.franzandel.moviesubmission.domain.model.FavouriteMovieRes
import com.franzandel.moviesubmission.domain.usecase.MoviesUseCase
import com.franzandel.moviesubmission.presentation.favourite.model.FavouriteMovieResUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Franz Andel on 10/08/21.
 * Android Engineer
 */

@HiltViewModel
class FavouriteMoviesVM @Inject constructor(
    private val moviesUseCase: MoviesUseCase,
    private val coroutineThread: CoroutineThread,
    private val mapper: BaseMapper<List<FavouriteMovieRes>, List<FavouriteMovieResUI>>
) : BaseViewModel() {

    private val _favouriteMovies = MutableLiveData<List<FavouriteMovieResUI>>()
    val favouriteMovies: LiveData<List<FavouriteMovieResUI>> = _favouriteMovies

    fun getFavouriteMovies() {
        viewModelScope.launch(coroutineThread.background()) {
            when (val result = moviesUseCase.getFavouriteMovies()) {
                is Result.Success -> {
                    val favouriteMoviesResUI = mapper.map(result.data)
                    _favouriteMovies.postValue(favouriteMoviesResUI)
                }
                is Result.Error -> _error.postValue(result.error.localizedMessage)
            }
        }
    }
}