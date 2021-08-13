package com.franzandel.moviesubmission.presentation.dashboard.activity

import androidx.activity.viewModels
import com.franzandel.moviesubmission.core.presentation.activity.BaseActivityVM
import com.franzandel.moviesubmission.databinding.ActivityDashboardBinding
import com.franzandel.moviesubmission.presentation.dashboard.adapter.TabPagerAdapter
import com.franzandel.moviesubmission.presentation.dashboard.vm.DashboardVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardActivity : BaseActivityVM<DashboardVM, ActivityDashboardBinding>() {

    private val viewModel: DashboardVM by viewModels()

    override fun onActivityCreated() {
        val sectionsPagerAdapter = TabPagerAdapter(this, supportFragmentManager)
        val viewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        binding.tabs.setupWithViewPager(viewPager)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getMovies()
    }

    override fun getVM(): DashboardVM = viewModel

    override fun getViewBinding(): ActivityDashboardBinding =
        ActivityDashboardBinding.inflate(layoutInflater)
}