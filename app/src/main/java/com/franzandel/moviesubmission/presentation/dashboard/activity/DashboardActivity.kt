package com.franzandel.moviesubmission.presentation.dashboard.activity

import androidx.activity.viewModels
import com.franzandel.moviesubmission.R
import com.franzandel.moviesubmission.core.external.extension.observe
import com.franzandel.moviesubmission.core.presentation.activity.BaseActivityVM
import com.franzandel.moviesubmission.databinding.ActivityDashboardBinding
import com.franzandel.moviesubmission.presentation.dashboard.adapter.TabPagerAdapter
import com.franzandel.moviesubmission.presentation.dashboard.vm.DashboardVM
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DashboardActivity : BaseActivityVM<DashboardVM, ActivityDashboardBinding>() {

    private val viewModel: DashboardVM by viewModels()

    override fun onActivityCreated() {
        setupTabLayout()
        setupObserver()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getMovies()
        viewModel.getFavouriteMovies()
    }

    private fun setupTabLayout() {
        val sectionsPagerAdapter = TabPagerAdapter(this)
        val viewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter

        TabLayoutMediator(
            binding.tabs, viewPager
        ) { tab: TabLayout.Tab, position: Int ->
            val title = when (position) {
                0 -> R.string.popular_movies_tab_title
                1 -> R.string.top_rated_movies_tab_title
                else -> R.string.favourite_movies_tab_title
            }
            tab.text = getString(title)
        }.attach()
    }

    private fun setupObserver() {
        observe(viewModel.favouriteMovies) { favouriteMoviesResUI ->
            updateFavouriteTabCount(favouriteMoviesResUI.size)
        }
    }

    private fun updateFavouriteTabCount(count: Int) {
        val favouriteTab = binding.tabs.getTabAt(2)
        if (count == 0)
            favouriteTab?.removeBadge()
        else
            favouriteTab?.orCreateBadge?.number = count
    }

    override fun getVM(): DashboardVM = viewModel

    override fun getViewBinding(): ActivityDashboardBinding =
        ActivityDashboardBinding.inflate(layoutInflater)
}