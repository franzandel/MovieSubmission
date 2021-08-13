package com.franzandel.moviesubmission.core.presentation.dialogfragment

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.franzandel.moviesubmission.R

/**
 * Created by Franz Andel on 13/08/21.
 * Android Engineer
 */

class LoadingDialog : DialogFragment() {

    companion object {
        fun newInstance(): LoadingDialog = LoadingDialog()

        private const val TAG = "LoadingDialog"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.df_loading, container)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDialogUI()
    }

    private fun setupDialogUI() {
        dialog?.apply {
            setCanceledOnTouchOutside(false)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            window?.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }
    }

    fun show(fragmentManager: FragmentManager) {
        if (!isAdded)
            show(fragmentManager, TAG)
    }

    fun hide() {
        if (isAdded || isVisible)
            dismiss()
    }
}
