package com.franzandel.moviesubmission.presentation.popularmovies.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.franzandel.moviesubmission.core.external.extension.observe
import com.franzandel.moviesubmission.core.presentation.fragment.BaseFragmentVM
import com.franzandel.moviesubmission.data.consts.RecyclerViewConst
import com.franzandel.moviesubmission.databinding.FragmentPopularMoviesBinding
import com.franzandel.moviesubmission.presentation.dashboard.vm.DashboardVM
import com.franzandel.moviesubmission.presentation.navigation.MoviesNavigation
import com.franzandel.moviesubmission.presentation.popularmovies.adapter.PopularMoviesAdapter
import com.franzandel.moviesubmission.presentation.popularmovies.vm.PopularMoviesVM
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PopularMoviesFragment : BaseFragmentVM<DashboardVM, FragmentPopularMoviesBinding>() {

    @Inject
    lateinit var navigation: MoviesNavigation

    private val dashboardVM: DashboardVM by activityViewModels()

    private val popularMoviesVM: PopularMoviesVM by activityViewModels()

    private val adapter by lazy {
        PopularMoviesAdapter(popularMoviesVM, navigation)
    }

    override fun onFragmentCreateView() {
        setupRV()
        setupObserver()
    }

    private fun setupRV() {
        viewBinding.rvPopularMovies.layoutManager = GridLayoutManager(
            requireContext(),
            RecyclerViewConst.GRID_SPAN_COUNT
        )
        viewBinding.rvPopularMovies.adapter = adapter
    }

    private fun setupObserver() {
        viewLifecycleOwner.observe(dashboardVM.popularMovies) { popularMoviesResUI ->
            adapter.submitList(popularMoviesResUI)
            adapter.notifyDataSetChanged()
        }

        viewLifecycleOwner.observe(dashboardVM.error) { errorMessage ->
            Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
        }

        viewLifecycleOwner.observe(popularMoviesVM.insertFavouriteMovie) { (popularMovieResUI, itemPosition) ->
            val isFavourite = true
            popularMovieResUI.isFavourite = isFavourite
            dashboardVM.updateTopRatedMovies(popularMovieResUI.id, isFavourite)
            dashboardVM.getFavouriteMovies()
            adapter.notifyItemChanged(itemPosition)
        }

        viewLifecycleOwner.observe(popularMoviesVM.deleteFavouriteMovie) { (popularMovieResUI, itemPosition) ->
            val isFavourite = false
            popularMovieResUI.isFavourite = isFavourite
            dashboardVM.updateTopRatedMovies(popularMovieResUI.id, isFavourite)
            dashboardVM.getFavouriteMovies()
            adapter.notifyItemChanged(itemPosition)
        }
    }

    override fun getVM(): DashboardVM = dashboardVM

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPopularMoviesBinding =
        FragmentPopularMoviesBinding.inflate(inflater, container, false)
}