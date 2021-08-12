package com.franzandel.moviesubmission.presentation.popularmovies.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.franzandel.moviesubmission.core.data.wrapper.Result
import com.franzandel.moviesubmission.core.external.coroutine.CoroutineThread
import com.franzandel.moviesubmission.core.mapper.BaseMapper
import com.franzandel.moviesubmission.core.presentation.vm.BaseViewModel
import com.franzandel.moviesubmission.domain.model.FavouriteMovieReq
import com.franzandel.moviesubmission.domain.usecase.MoviesUseCase
import com.franzandel.moviesubmission.presentation.popularmovies.model.PopularMovieResUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularMoviesVM @Inject constructor(
    private val moviesUseCase: MoviesUseCase,
    private val coroutineThread: CoroutineThread,
    private val mapper: BaseMapper<PopularMovieResUI, FavouriteMovieReq>
) : BaseViewModel() {

    private val _insertFavouriteMovie = MutableLiveData<Unit>()
    val insertFavouriteMovie: LiveData<Unit> = _insertFavouriteMovie

    fun insertFavouriteMovie(popularMovieResUI: PopularMovieResUI) {
        viewModelScope.launch(coroutineThread.background()) {
            val favouriteMovieReq = mapper.map(popularMovieResUI)
            when (val result = moviesUseCase.insertFavouriteMovie(favouriteMovieReq)) {
                is Result.Success -> _insertFavouriteMovie.postValue(result.data)
                is Result.Error -> _error.postValue(result.error.localizedMessage)
            }
        }
    }

    private val _deleteFavouriteMovie = MutableLiveData<Unit>()
    val deleteFavouriteMovie: LiveData<Unit> = _deleteFavouriteMovie

    fun deleteFavouriteMovie(popularMovieResUI: PopularMovieResUI) {
        viewModelScope.launch(coroutineThread.background()) {
            val favouriteMovieReq = mapper.map(popularMovieResUI)
            when (val result = moviesUseCase.deleteFavouriteMovie(favouriteMovieReq)) {
                is Result.Success -> _deleteFavouriteMovie.postValue(result.data)
                is Result.Error -> _error.postValue(result.error.localizedMessage)
            }
        }
    }
}