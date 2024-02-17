package com.jean.moviesarchitectcoders.domain.usecases

import com.jean.moviesarchitectcoders.domain.repository.MoviesRepository
import com.jean.moviesarchitectcoders.domain.repository.RegionRepository
import com.jean.moviesarchitectcoders.domain.utils.CoroutineTestRule
import com.jean.moviesarchitectcoders.domain.utils.movies
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class GetMoviesUseCaseTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK(relaxed = true)
    lateinit var moviesRepository: MoviesRepository

    @MockK
    lateinit var regionRepository: RegionRepository

    private lateinit var sut: GetMoviesUseCase

    @Before
    fun setUp() {
        sut = GetMoviesUseCase(
            moviesRepository,
            regionRepository
        )
    }

    @Test
    fun `verify when user deny permissions, then call get movies with default region`() = runTest {
        val defaultRegion = "US"

        sut.invoke(hasPermissions = false)

        coVerify { moviesRepository.getMovies(defaultRegion) }
    }

    @Test
    fun `verify when find user region, return a movie list`() = runTest {
        val expectedMovies = movies

        coEvery { regionRepository.findLastRegion() } returns "ES"
        coEvery { moviesRepository.getMovies("ES") } returns Result.success(flowOf(movies))


        sut.invoke(hasPermissions = true).onSuccess { moviesResult ->
            assertEquals(expectedMovies, moviesResult.first())
        }
    }

}