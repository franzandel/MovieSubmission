package com.franzandel.moviesubmission.core.presentation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by Franz Andel on 11/08/21.
 * Android Engineer
 */

abstract class BaseViewModel : ViewModel() {

    protected val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    protected val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading
}