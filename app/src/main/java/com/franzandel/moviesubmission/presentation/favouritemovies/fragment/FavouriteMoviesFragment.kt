package com.franzandel.moviesubmission.presentation.favouritemovies.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.franzandel.moviesubmission.core.external.extension.observe
import com.franzandel.moviesubmission.core.presentation.fragment.BaseFragmentVM
import com.franzandel.moviesubmission.data.consts.RecyclerViewConst
import com.franzandel.moviesubmission.databinding.FragmentFavouriteMoviesBinding
import com.franzandel.moviesubmission.presentation.dashboard.vm.DashboardVM
import com.franzandel.moviesubmission.presentation.favouritemovies.adapter.FavouriteMoviesAdapter
import com.franzandel.moviesubmission.presentation.navigation.MoviesNavigation
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavouriteMoviesFragment :
    BaseFragmentVM<DashboardVM, FragmentFavouriteMoviesBinding>() {

    @Inject
    lateinit var navigation: MoviesNavigation

    private val viewModel: DashboardVM by activityViewModels()

    private val adapter by lazy {
        FavouriteMoviesAdapter { favouriteMovieResUI ->
            navigation.goToDetailFavouriteMovie(favouriteMovieResUI)
        }
    }

    override fun onFragmentCreateView() {
        setupRV()
        setupObserver()
        viewModel.getFavouriteMovies()
    }

    private fun setupRV() {
        viewBinding.rvFavouriteMovies.layoutManager = GridLayoutManager(
            requireContext(),
            RecyclerViewConst.GRID_SPAN_COUNT
        )
        viewBinding.rvFavouriteMovies.adapter = adapter
    }

    private fun setupObserver() {
        viewLifecycleOwner.observe(viewModel.favouriteMovies) { favouriteMovieResUI ->
            adapter.submitList(favouriteMovieResUI)
            adapter.notifyDataSetChanged()
        }

        viewLifecycleOwner.observe(viewModel.error) { errorMessage ->
            Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
        }
    }

    override fun getVM(): DashboardVM = viewModel

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFavouriteMoviesBinding =
        FragmentFavouriteMoviesBinding.inflate(inflater, container, false)
}