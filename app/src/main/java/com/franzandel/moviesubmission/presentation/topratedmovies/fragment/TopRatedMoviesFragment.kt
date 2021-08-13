package com.franzandel.moviesubmission.presentation.topratedmovies.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.franzandel.moviesubmission.core.external.extension.observe
import com.franzandel.moviesubmission.core.presentation.fragment.BaseFragmentVM
import com.franzandel.moviesubmission.data.consts.RecyclerViewConst
import com.franzandel.moviesubmission.databinding.FragmentTopRatedMoviesBinding
import com.franzandel.moviesubmission.presentation.dashboard.vm.DashboardVM
import com.franzandel.moviesubmission.presentation.navigation.MoviesNavigation
import com.franzandel.moviesubmission.presentation.topratedmovies.adapter.TopRatedMoviesAdapter
import com.franzandel.moviesubmission.presentation.topratedmovies.model.TopRatedMovieResUI
import com.franzandel.moviesubmission.presentation.topratedmovies.vm.TopRatedMoviesVM
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TopRatedMoviesFragment : BaseFragmentVM<DashboardVM, FragmentTopRatedMoviesBinding>() {

    @Inject
    lateinit var navigation: MoviesNavigation

    private val dashboardVM: DashboardVM by activityViewModels()

    private val topRatedMoviesVM: TopRatedMoviesVM by activityViewModels()

    private var topRatedMovieResUI: TopRatedMovieResUI? = null
    private var ivFavourite: ImageView? = null

    private val adapter by lazy {
        TopRatedMoviesAdapter({ topRatedMovieResUI, ivFavourite ->
            this.ivFavourite = ivFavourite
            this.topRatedMovieResUI = topRatedMovieResUI

            if (ivFavourite.isSelected)
                topRatedMoviesVM.deleteFavouriteMovie(topRatedMovieResUI)
            else
                topRatedMoviesVM.insertFavouriteMovie(topRatedMovieResUI)
        }, { topRatedMovieResUI ->
//            navigation.goToDetailPopularMovie(topRatedMovieResUI)
        })
    }

    override fun onFragmentCreateView() {
        setupRV()
        setupObserver()
    }

    private fun setupRV() {
        viewBinding.rvTopRatedMovies.layoutManager = GridLayoutManager(
            requireContext(),
            RecyclerViewConst.GRID_SPAN_COUNT
        )
        viewBinding.rvTopRatedMovies.adapter = adapter
    }

    private fun setupObserver() {
        viewLifecycleOwner.observe(dashboardVM.topRatedMovies) { topRatedMovieResUI ->
            adapter.submitList(topRatedMovieResUI)
            adapter.notifyDataSetChanged()
        }

        viewLifecycleOwner.observe(dashboardVM.error) { errorMessage ->
            Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
        }

        viewLifecycleOwner.observe(topRatedMoviesVM.insertFavouriteMovie) {
            val isFavourite = true
            ivFavourite?.isSelected = isFavourite

            topRatedMovieResUI?.let { topRatedMovieResUI ->
                topRatedMovieResUI.isFavourite = isFavourite
                dashboardVM.updatePopularMovies(topRatedMovieResUI.id, isFavourite)
            }
        }

        viewLifecycleOwner.observe(topRatedMoviesVM.deleteFavouriteMovie) {
            val isFavourite = false
            ivFavourite?.isSelected = isFavourite

            topRatedMovieResUI?.let { topRatedMovieResUI ->
                topRatedMovieResUI.isFavourite = isFavourite
                dashboardVM.updatePopularMovies(topRatedMovieResUI.id, isFavourite)
            }
        }
    }

    override fun getVM(): DashboardVM = dashboardVM

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTopRatedMoviesBinding =
        FragmentTopRatedMoviesBinding.inflate(inflater, container, false)
}