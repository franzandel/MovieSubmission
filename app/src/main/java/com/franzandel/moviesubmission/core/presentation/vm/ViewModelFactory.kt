package com.franzandel.moviesubmission.core.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject
import javax.inject.Provider

/**
 * Created by Franz Andel on 11/08/21.
 * Android Engineer
 */

@Suppress("UNCHECKED_CAST")
@ViewModelScoped
class ViewModelFactory @Inject constructor(
    private val viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        viewModels[modelClass]?.get() as T
}