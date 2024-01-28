package com.jean.moviesarchitectcoders.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jean.moviesarchitectcoders.R
import com.jean.moviesarchitectcoders.databinding.FragmentMovieBinding
import com.jean.moviesarchitectcoders.presentation.adapter.FavoriteAdapter
import com.jean.moviesarchitectcoders.presentation.adapter.MovieAdapter
import com.jean.moviesarchitectcoders.presentation.viewmodel.MovieViewModel
import com.jean.moviesarchitectcoders.utils.PermissionHandler
import com.jean.moviesarchitectcoders.utils.Permissions
import com.jean.moviesarchitectcoders.utils.launchAndCollect
import com.jean.moviesarchitectcoders.utils.toAndroidId
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

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGrated ->
        viewModel.getMovies(hasPermission = isGrated)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        validatePermission()
        setupAdapters()
        setupRecyclers()
        setupObserver()
    }

    private fun validatePermission() {
        permissionHandler.validatePermission(
            permission = Permissions.COARSE_LOCATION,
            permissionGranted = {
                viewModel.getMovies(hasPermission = true)
            },
            requirePermission = {
                requestPermissionLauncher.launch(Permissions.COARSE_LOCATION.toAndroidId())
            }
        )
    }

    private fun setupAdapters() {
        movieAdapter = MovieAdapter(onMovieClick = { movieId -> navigateToDetail(movieId) })

        favoriteAdapter = FavoriteAdapter(onFavoriteClick = { movieId -> navigateToDetail(movieId) })
    }

    private fun navigateToDetail(movieId: Int) {
        val navAction = MovieFragmentDirections.actionMovieToDetail(movieId)
        findNavController().navigate(navAction)
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