package com.franzandel.moviesubmission.presentation.dashboard.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.franzandel.moviesubmission.presentation.favouritemovies.fragment.FavouriteMoviesFragment
import com.franzandel.moviesubmission.presentation.popularmovies.fragment.PopularMoviesFragment
import com.franzandel.moviesubmission.presentation.topratedmovies.fragment.TopRatedMoviesFragment

class TabPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment =
        when (position) {
            0 -> PopularMoviesFragment()
            1 -> TopRatedMoviesFragment()
            else -> FavouriteMoviesFragment()
        }
}