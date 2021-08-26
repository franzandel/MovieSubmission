package com.franzandel.moviesubmission.presentation.popularmovies.bindingadapter

import android.graphics.drawable.Drawable
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/**
 * Created by Franz Andel on 26/08/21.
 * Android Engineer
 */

@BindingAdapter("imageUrl", "placeholder")
fun loadImage(imageView: AppCompatImageView, url: String, placeHolder: Drawable) {
    Glide.with(imageView.context)
        .load(url)
        .centerCrop()
        .placeholder(placeHolder)
        // TODO: Size is not equivalent without this, but defining in VH, is equivalent by itself
        .override(600, 800)
        .into(imageView)
}