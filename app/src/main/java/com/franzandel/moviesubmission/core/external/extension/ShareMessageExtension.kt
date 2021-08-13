package com.franzandel.moviesubmission.core.external.extension

import android.app.Activity
import androidx.core.app.ShareCompat

/**
 * Created by Franz Andel on 13/08/21.
 * Android Engineer
 */

fun Activity.showShareMessage(chooserTitle: String, text: String) {
    val mimeType = "text/plain"
    ShareCompat.IntentBuilder.from(this)
        .setType(mimeType)
        .setChooserTitle(chooserTitle)
        .setText(text)
        .startChooser()
}