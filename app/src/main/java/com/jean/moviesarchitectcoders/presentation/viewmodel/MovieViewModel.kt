package com.jean.moviesarchitectcoders.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jean.moviesarchitectcoders.domain.models.Movie
import com.jean.moviesarchitectcoders.domain.usecases.GetFavoriteMoviesUseCase
import com.jean.moviesarchitectcoders.domain.usecases.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase,
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    fun getMovies(hasPermission: Boolean) {
        viewModelScope.launch {
            _state.update { state -> state.copy(isLoading = true) }
            getMoviesUseCase(hasPermissions = hasPermission)
                .onSuccess { movieResult ->
                    movieResult.collect { movies ->
                        _state.update { state ->
                            state.copy(
                                isLoading = false,
                                hasMovies = movies.isNotEmpty(),
                                movies = movies,
                                hasError = false
                            )
                        }
                    }
                }.onFailure {
                    _state.update { state ->
                        state.copy(
                            isLoading = false,
                            hasError = true
                        )
                    }
                }
        }
    }

    fun getFavoriteMovies() {
        viewModelScope.launch {
            getFavoriteMoviesUseCase()
                .onSuccess { favoritesResult ->
                    favoritesResult.collect { favorites ->
                        _state.update { state ->
                            state.copy(
                                hasFavorites = favorites.isNotEmpty(),
                                favorites = favorites,
                                hasError = false
                            )
                        }
                    }
                }
        }
    }

    data class UiState(
        val isLoading: Boolean = false,
        val hasMovies: Boolean = false,
        val hasFavorites: Boolean = false,
        val movies: List<Movie> = listOf(),
        val favorites: List<Movie> = listOf(),
        val hasError: Boolean = false
    )

}