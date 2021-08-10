package com.franzandel.moviesubmission.core.presentation

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

/**
 * Created by Franz Andel on 10/08/21.
 * Android Engineer
 */

abstract class BaseAdapter<Type, VH : RecyclerView.ViewHolder, VB : ViewBinding>(diffCallback: DiffUtil.ItemCallback<Type>) :
    ListAdapter<Type, VH>(diffCallback) {

    abstract fun getViewBinding(parent: ViewGroup): VB

    abstract fun getViewHolder(viewBinding: VB): VH

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH =
        getViewHolder(getViewBinding(parent))
}