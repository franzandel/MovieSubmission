package com.franzandel.moviesubmission.core.external.extension

import android.content.Context
import android.content.Intent

/**
 * Created by Franz Andel on 12/08/21.
 * Android Engineer
 */

fun Context.goTo(clazz: Class<*>, bundle: (Intent.() -> Unit)? = null) {
    Intent(this, clazz).run {
        bundle?.invoke(this)
        startActivity(this)
    }
}