package com.franzandel.moviesubmission.presentation.topratedmovies.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.franzandel.moviesubmission.data.consts.RecyclerViewConst
import com.franzandel.moviesubmission.databinding.FragmentTopRatedMoviesBinding
import com.franzandel.moviesubmission.presentation.topratedmovies.adapter.TopRatedMoviesAdapter

class TopRatedMoviesFragment : Fragment() {

    private var _binding: FragmentTopRatedMoviesBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTopRatedMoviesBinding.inflate(inflater, container, false)
        val root = binding.root

        setupRV()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRV() {
        val adapter = TopRatedMoviesAdapter()
        binding.rvTopRatedMovies.layoutManager = GridLayoutManager(
            requireContext(),
            RecyclerViewConst.GRID_SPAN_COUNT
        )
        binding.rvTopRatedMovies.adapter = adapter
        adapter.submitList(listOf("", "", ""))
    }
}