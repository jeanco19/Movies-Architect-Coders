package com.jean.moviesarchitectcoders.viewmodel

import app.cash.turbine.test
import com.jean.moviesarchitectcoders.data.repository.MoviesRepositoryImpl
import com.jean.moviesarchitectcoders.data.repository.RegionRepositoryImpl
import com.jean.moviesarchitectcoders.domain.models.Movie
import com.jean.moviesarchitectcoders.domain.usecases.GetFavoriteMoviesUseCase
import com.jean.moviesarchitectcoders.domain.usecases.GetMoviesUseCase
import com.jean.moviesarchitectcoders.presentation.viewmodel.MovieViewModel
import com.jean.moviesarchitectcoders.utils.CoroutineTestRule
import com.jean.moviesarchitectcoders.utils.FakeLocationDatasource
import com.jean.moviesarchitectcoders.utils.FakeMovieLocalDatasource
import com.jean.moviesarchitectcoders.utils.FakeMovieRemoteDatasource
import com.jean.moviesarchitectcoders.utils.expectedMovies
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieViewModelTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Test
    fun `verify when call get movies and local data is empty, then return remote movies`() = runTest {
        val remoteData = expectedMovies
        val sut = buildViewModelWith(remoteData = remoteData)

        sut.getMovies(hasPermission = true)

        sut.state.test {
            assertEquals(MovieViewModel.UiState(), awaitItem())
            assertEquals(MovieViewModel.UiState(isLoading = true), awaitItem())
            assertEquals(MovieViewModel.UiState(movies = expectedMovies, hasMovies = true), awaitItem())
            cancel()
        }
    }

    @Test
    fun `verify when call get movies have local data, then return local movies`() = runTest {
        val localData = expectedMovies
        val sut = buildViewModelWith(localData = localData)

        sut.getMovies(hasPermission = true)

        sut.state.test {
            assertEquals(MovieViewModel.UiState(), awaitItem())
            assertEquals(MovieViewModel.UiState(isLoading = true), awaitItem())
            assertEquals(MovieViewModel.UiState(movies = expectedMovies, hasMovies = true), awaitItem())
            cancel()
        }
    }

    private fun buildViewModelWith(
        remoteData: List<Movie> = emptyList(),
        localData: List<Movie> = emptyList()
    ): MovieViewModel {
        val locationDataSource = FakeLocationDatasource()
        val regionRepository = RegionRepositoryImpl(locationDataSource)

        val remoteMoviesDatasource = FakeMovieRemoteDatasource().apply { movies = remoteData }
        val localMoviesDatasource = FakeMovieLocalDatasource().apply { moviesInMemory = localData.toMutableList() }
        val moviesRepository = MoviesRepositoryImpl(remoteMoviesDatasource, localMoviesDatasource)

        val getMoviesUseCase = GetMoviesUseCase(moviesRepository, regionRepository)
        val getFavoriteMoviesUseCase = GetFavoriteMoviesUseCase(moviesRepository)

        return MovieViewModel(getFavoriteMoviesUseCase, getMoviesUseCase)
    }

}