package com.franzandel.moviesubmission.core.external.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

/**
 * Created by Franz Andel on 11/08/21.
 * Android Engineer
 */

inline fun <AllType> LifecycleOwner.observe(
    liveData: LiveData<AllType>,
    crossinline action: (allType: AllType) -> Unit
) {
    liveData.observe(this, {
        it?.let {
            action(it)
        }
    })
}