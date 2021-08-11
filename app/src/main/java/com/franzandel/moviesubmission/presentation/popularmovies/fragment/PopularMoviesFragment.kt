package com.franzandel.moviesubmission.presentation.popularmovies.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.franzandel.moviesubmission.core.external.extension.observe
import com.franzandel.moviesubmission.data.consts.RecyclerViewConst
import com.franzandel.moviesubmission.databinding.FragmentPopularMoviesBinding
import com.franzandel.moviesubmission.presentation.dashboard.vm.DashboardVM
import com.franzandel.moviesubmission.presentation.popularmovies.adapter.PopularMoviesAdapter
import com.franzandel.moviesubmission.presentation.popularmovies.vm.PopularMoviesVM

class PopularMoviesFragment : Fragment() {

    private val viewModel: DashboardVM by activityViewModels()

    private lateinit var pageViewModel: PopularMoviesVM
    private var _binding: FragmentPopularMoviesBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProvider(this).get(PopularMoviesVM::class.java)
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
        val adapter = PopularMoviesAdapter()
        binding.rvPopularMovies.layoutManager = GridLayoutManager(
            requireContext(),
            RecyclerViewConst.GRID_SPAN_COUNT
        )
        binding.rvPopularMovies.adapter = adapter
        adapter.submitList(listOf("", ""))
    }

    private fun setupObserver() {
        viewLifecycleOwner.observe(viewModel.popularMovies) {
            Log.d("1234", it[0].title)
        }

        viewLifecycleOwner.observe(viewModel.error) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }
}