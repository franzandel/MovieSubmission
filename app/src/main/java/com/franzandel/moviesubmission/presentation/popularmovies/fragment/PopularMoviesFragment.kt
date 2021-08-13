package com.franzandel.moviesubmission.presentation.popularmovies.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.franzandel.moviesubmission.core.external.extension.observe
import com.franzandel.moviesubmission.data.consts.RecyclerViewConst
import com.franzandel.moviesubmission.databinding.FragmentPopularMoviesBinding
import com.franzandel.moviesubmission.presentation.dashboard.vm.DashboardVM
import com.franzandel.moviesubmission.presentation.navigation.MoviesNavigation
import com.franzandel.moviesubmission.presentation.popularmovies.adapter.PopularMoviesAdapter
import com.franzandel.moviesubmission.presentation.popularmovies.model.PopularMovieResUI
import com.franzandel.moviesubmission.presentation.popularmovies.vm.PopularMoviesVM
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PopularMoviesFragment : Fragment() {

    @Inject
    lateinit var navigation: MoviesNavigation

    private val dashboardVM: DashboardVM by activityViewModels()

    private val popularMoviesVM: PopularMoviesVM by activityViewModels()

    private var _binding: FragmentPopularMoviesBinding? = null
    private val binding get() = _binding!!

    private var popularMovieResUI: PopularMovieResUI? = null
    private var ivFavourite: ImageView? = null

    private val adapter by lazy {
        PopularMoviesAdapter({ popularMovieResUI, ivFavourite ->
            this.ivFavourite = ivFavourite
            this.popularMovieResUI = popularMovieResUI

            if (ivFavourite.isSelected)
                popularMoviesVM.deleteFavouriteMovie(popularMovieResUI)
            else
                popularMoviesVM.insertFavouriteMovie(popularMovieResUI)
        }, { popularMovieResUI ->
            navigation.goToDetailPopularMovie(popularMovieResUI)
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPopularMoviesBinding.inflate(inflater, container, false)
        val root = binding.root

        setupRV()
        setupObserver()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRV() {
        binding.rvPopularMovies.layoutManager = GridLayoutManager(
            requireContext(),
            RecyclerViewConst.GRID_SPAN_COUNT
        )
        binding.rvPopularMovies.adapter = adapter
    }

    private fun setupObserver() {
        viewLifecycleOwner.observe(dashboardVM.popularMovies) {
            adapter.submitList(it)
        }

        viewLifecycleOwner.observe(dashboardVM.error) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

        viewLifecycleOwner.observe(popularMoviesVM.insertFavouriteMovie) {
            ivFavourite?.isSelected = true
            popularMovieResUI?.isFavourite = true
        }

        viewLifecycleOwner.observe(popularMoviesVM.deleteFavouriteMovie) {
            ivFavourite?.isSelected = false
            popularMovieResUI?.isFavourite = false
        }
    }
}