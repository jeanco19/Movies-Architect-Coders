package com.jean.moviesarchitectcoders.data.repository

import com.jean.moviesarchitectcoders.data.datasource.movies.local.MovieLocalDatasource
import com.jean.moviesarchitectcoders.data.datasource.movies.remote.MovieRemoteDatasource
import com.jean.moviesarchitectcoders.data.utils.CoroutineTestRule
import com.jean.moviesarchitectcoders.data.utils.expectedMovies
import com.jean.moviesarchitectcoders.data.utils.movie
import com.jean.moviesarchitectcoders.domain.models.Movie
import com.jean.moviesarchitectcoders.domain.repository.MoviesRepository
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MoviesRepositoryTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @MockK
    lateinit var remoteDatasource: MovieRemoteDatasource

    @MockK(relaxUnitFun = true)
    lateinit var localDatasource: MovieLocalDatasource

    private lateinit var sut: MoviesRepository

    @Before
    fun setUp() {
        sut = MoviesRepositoryImpl(remoteDatasource, localDatasource)
    }

    @Test
    fun `verify when the local data is empty, then calls then remote datasource`() = runTest {
        val localMovies = listOf<Movie>()

        coEvery { localDatasource.getMovies() } returns flowOf(localMovies)

        sut.getMovies("US").first()

        coVerify(exactly = 1) { remoteDatasource.getNowPlayingMovies("US") }
    }

    @Test
    fun `given existing local data, when call get movies, then returns the local data`() = runTest {
        val localMovies = expectedMovies

        coEvery { localDatasource.saveMovie(expectedMovies.first()) }

        sut.getMovies("US").first().let { result ->
            result.onSuccess {  movies ->
                assertEquals(localMovies.first(), movies.first())
            }
        }

        coVerify(exactly = 1) { localDatasource.getMovies()  }
    }

    @Test
    fun `given not favorite movie, when call save function, then return the movie as favorite`() = runTest {
        val expectedMovie = movie

        sut.saveFavorite(movie.copy(isFavorite = false))

        sut.getFavorites().first().let { result ->
            result.onSuccess { movies ->
                assertEquals(expectedMovie, movies.first())
                assertTrue(movies.first().isFavorite)
            }
        }
    }

    @Test
    fun `verify when call movie by id, then return the right movie`() = runTest {
        val expectedMovie = movie

        coJustRun { localDatasource.saveFavorite(movie) }

        sut.getMovieById(movie.id).first().let { result ->
            result.onSuccess {  movie ->
                assertEquals(expectedMovie, movie)
            }
        }
    }

}