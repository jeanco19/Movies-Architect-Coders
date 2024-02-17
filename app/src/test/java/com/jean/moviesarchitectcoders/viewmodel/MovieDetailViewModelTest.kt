package com.jean.moviesarchitectcoders.viewmodel

import app.cash.turbine.test
import com.jean.moviesarchitectcoders.domain.usecases.GetMovieByIdUseCase
import com.jean.moviesarchitectcoders.domain.usecases.SaveFavoriteMovieUseCase
import com.jean.moviesarchitectcoders.presentation.viewmodel.MovieDetailViewModel
import com.jean.moviesarchitectcoders.utils.CoroutineTestRule
import com.jean.moviesarchitectcoders.utils.expectedMovie
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieDetailViewModelTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @MockK
    lateinit var getMovieByIdUseCase: GetMovieByIdUseCase

    @MockK
    lateinit var saveFavoriteMovieUseCase: SaveFavoriteMovieUseCase

    private lateinit var sut: MovieDetailViewModel

    @Before
    fun setUp() {
        sut = MovieDetailViewModel(getMovieByIdUseCase, saveFavoriteMovieUseCase)
    }

    @Test
    fun `verify when send the movie id, then return the movie data`() = runTest {
        val movieId = 1
        coEvery { getMovieByIdUseCase(movieId) } returns Result.success(flowOf(expectedMovie))

        sut.getMovieById(movieId)

        sut.state.test {
            assertEquals(MovieDetailViewModel.UiState(), awaitItem())
            assertEquals(MovieDetailViewModel.UiState(isLoading = true), awaitItem())
            assertEquals(MovieDetailViewModel.UiState(isLoading = false, movie = expectedMovie), awaitItem())
            cancel()
        }
    }

}