package com.franzandel.moviesubmission.presentation.dashboard.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.franzandel.moviesubmission.core.data.wrapper.Result
import com.franzandel.moviesubmission.core.external.coroutine.CoroutineThread
import com.franzandel.moviesubmission.core.mapper.BaseMapper
import com.franzandel.moviesubmission.core.presentation.vm.BaseViewModel
import com.franzandel.moviesubmission.domain.model.MovieGenreRes
import com.franzandel.moviesubmission.domain.usecase.MoviesUseCase
import com.franzandel.moviesubmission.presentation.popularmovies.model.PopularMovieResUI
import com.franzandel.moviesubmission.presentation.topratedmovies.model.TopRatedMovieResUI
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
    private val coroutineThread: CoroutineThread,
    private val popularMovieResUIMapper: BaseMapper<List<MovieGenreRes>, List<PopularMovieResUI>>,
    private val topRatedMovieResUIMapper: BaseMapper<List<MovieGenreRes>, List<TopRatedMovieResUI>>
) : BaseViewModel() {

    private lateinit var popularMoviesResUI: List<PopularMovieResUI>
    private lateinit var topRatedMoviesResUI: List<TopRatedMovieResUI>

    private val _popularMovies = MutableLiveData<List<PopularMovieResUI>>()
    val popularMovies: LiveData<List<PopularMovieResUI>> = _popularMovies

    private val _topRatedMovies = MutableLiveData<List<TopRatedMovieResUI>>()
    val topRatedMovies: LiveData<List<TopRatedMovieResUI>> = _topRatedMovies

    fun getMovies() {
        _loading.value = true
        viewModelScope.launch(coroutineThread.background()) {
            when (val result = moviesUseCase.getMovies()) {
                is Result.Success -> {
                    popularMoviesResUI = popularMovieResUIMapper.map(result.data)
                    topRatedMoviesResUI = topRatedMovieResUIMapper.map(result.data)
                    _popularMovies.postValue(popularMoviesResUI)
                    _topRatedMovies.postValue(topRatedMoviesResUI)
                }
                is Result.Error -> _error.postValue(result.error.localizedMessage)
            }

            _loading.postValue(false)
        }
    }

    fun updatePopularMovies(id: Int, isFavourite: Boolean) {
        viewModelScope.launch(coroutineThread.default()) {
            popularMoviesResUI.find { popularMovieResUI ->
                popularMovieResUI.id == id
            }?.isFavourite = isFavourite

            _popularMovies.postValue(popularMoviesResUI)
        }
    }

    fun updateTopRatedMovies(id: Int, isFavourite: Boolean) {
        viewModelScope.launch(coroutineThread.default()) {
            topRatedMoviesResUI.find { topRatedMovieResUI ->
                topRatedMovieResUI.id == id
            }?.isFavourite = isFavourite

            _topRatedMovies.postValue(topRatedMoviesResUI)
        }
    }
}