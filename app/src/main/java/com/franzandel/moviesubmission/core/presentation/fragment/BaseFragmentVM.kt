package com.franzandel.moviesubmission.core.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.franzandel.moviesubmission.core.external.extension.observe
import com.franzandel.moviesubmission.core.presentation.dialogfragment.LoadingDialog
import com.franzandel.moviesubmission.core.presentation.vm.BaseViewModel
import java.lang.ref.WeakReference

/**
 * Created by Franz Andel on 13/08/21.
 * Android Engineer
 */

abstract class BaseFragmentVM<VM : ViewModel, VB : ViewBinding> : BaseFragment<VB>() {

    protected val loadingDialog by lazy {
        WeakReference(LoadingDialog())
    }

    abstract fun getVM(): VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setupObserver(getVM() as BaseViewModel)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun setupObserver(viewModel: BaseViewModel) {
        viewLifecycleOwner.observe(viewModel.loading) {
            if (it)
                loadingDialog.get()?.show(requireActivity().supportFragmentManager)
            else
                loadingDialog.get()?.hide()
        }
    }
}