package com.jean.moviesarchitectcoders.movie.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jean.moviesarchitectcoders.movie.R
import com.jean.moviesarchitectcoders.movie.databinding.FragmentMovieBinding
import com.jean.moviesarchitectcoders.movie.presentation.adapter.FavoriteAdapter
import com.jean.moviesarchitectcoders.movie.presentation.adapter.MovieAdapter
import com.jean.moviesarchitectcoders.movie.presentation.viewmodel.MovieViewModel
import com.jean.moviesarchitectcoders.movie.utils.PermissionHandler
import com.jean.moviesarchitectcoders.movie.utils.launchAndCollect
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MovieFragment : Fragment(R.layout.fragment_movie) {

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!

    private lateinit var movieAdapter: MovieAdapter
    private lateinit var favoriteAdapter: FavoriteAdapter

    @Inject lateinit var permissionHandler: PermissionHandler
    private val viewModel: MovieViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapters()
        setupRecyclers()
        setupObserver()
    }

    private fun setupAdapters() {
        movieAdapter = MovieAdapter(onMovieClick = { movieId ->
            // TODO handle movieID
        })

        favoriteAdapter = FavoriteAdapter(onFavoriteClick = { movieId ->
            // TODO handle movieID
        })
    }

    private fun setupRecyclers() {
        binding.rvMovies.apply {
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
            addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
            setHasFixedSize(true)
        }

        binding.rvFavorites.apply {
            adapter = favoriteAdapter
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
            setHasFixedSize(true)
        }
    }

    private fun setupObserver() {
        viewLifecycleOwner.launchAndCollect(viewModel.state) { uiState -> handleUiState(uiState) }
    }

    private fun handleUiState(uiState: MovieViewModel.UiState) = with(binding) {
        progressCircular.isVisible = uiState.isLoading
        llFavoritesBlock.isVisible = uiState.hasFavorites
        rvMovies.isVisible = uiState.hasMovies
        llMoviesError.isVisible = uiState.hasError
        movieAdapter.submitList(uiState.movies)
        favoriteAdapter.submitList(uiState.favorites)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}