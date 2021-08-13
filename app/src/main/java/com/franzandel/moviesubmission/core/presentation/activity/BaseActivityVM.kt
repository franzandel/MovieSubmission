package com.franzandel.moviesubmission.core.presentation.activity

import android.os.Bundle
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

abstract class BaseActivityVM<VM : ViewModel, VB : ViewBinding> : BaseActivity<VB>() {

    protected val loadingDialog by lazy {
        WeakReference(LoadingDialog())
    }

    abstract fun getVM(): VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupObserver(getVM() as BaseViewModel)
    }

    private fun setupObserver(viewModel: BaseViewModel) {
        observe(viewModel.loading) {
            if (it)
                loadingDialog.get()?.show(supportFragmentManager)
            else
                loadingDialog.get()?.hide()
        }
    }
}