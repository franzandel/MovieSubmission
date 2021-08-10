package com.franzandel.moviesubmission.presentation.popularmovies.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.franzandel.moviesubmission.databinding.FragmentPopularMoviesBinding
import com.franzandel.moviesubmission.presentation.popularmovies.adapter.PopularMoviesAdapter
import com.franzandel.moviesubmission.presentation.popularmovies.vm.PageViewModel

/**
 * A placeholder fragment containing a simple view.
 */
class PopularMoviesFragment : Fragment() {

    private lateinit var pageViewModel: PageViewModel
    private var _binding: FragmentPopularMoviesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProvider(this).get(PageViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPopularMoviesBinding.inflate(inflater, container, false)
        val root = binding.root

        setupRV()
        return root
    }

    private fun setupRV() {
        val adapter = PopularMoviesAdapter()
        binding.rvPopularMovies.layoutManager = GridLayoutManager(
            requireContext(),
            GRID_SPAN_COUNT
        )
        binding.rvPopularMovies.adapter = adapter
        adapter.submitList(listOf("", ""))
    }

    companion object {
        private const val GRID_SPAN_COUNT = 2

        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): PopularMoviesFragment {
            return PopularMoviesFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}