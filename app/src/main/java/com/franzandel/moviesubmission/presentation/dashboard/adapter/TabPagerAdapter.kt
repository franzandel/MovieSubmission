package com.franzandel.moviesubmission.presentation.dashboard.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.franzandel.moviesubmission.R
import com.franzandel.moviesubmission.presentation.favouritemovies.fragment.FavouriteMoviesFragment
import com.franzandel.moviesubmission.presentation.popularmovies.fragment.PopularMoviesFragment
import com.franzandel.moviesubmission.presentation.topratedmovies.fragment.TopRatedMoviesFragment

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class TabPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val tabTitles = arrayOf(
        R.string.popular_movies_tab_title,
        R.string.top_rated_movies_tab_title,
        R.string.favourite_movies_tab_title
    )

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> PopularMoviesFragment()
            1 -> TopRatedMoviesFragment()
            else -> FavouriteMoviesFragment()
        }

    override fun getPageTitle(position: Int): CharSequence =
        context.resources.getString(tabTitles[position])

    override fun getCount(): Int = tabTitles.size
}