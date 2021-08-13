package com.franzandel.moviesubmission.core.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/**
 * Created by Franz Andel on 13/08/21.
 * Android Engineer
 */

abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    private var _viewBinding: VB? = null
    protected val viewBinding: VB get() = _viewBinding!!

    abstract fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): VB

    abstract fun onFragmentCreateView()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _viewBinding = getViewBinding(inflater, container)
        onFragmentCreateView()
        return viewBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }
}