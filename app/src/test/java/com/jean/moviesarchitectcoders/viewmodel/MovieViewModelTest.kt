package com.jean.moviesarchitectcoders.viewmodel

import com.jean.moviesarchitectcoders.data.repository.MoviesRepositoryImpl
import com.jean.moviesarchitectcoders.data.repository.RegionRepositoryImpl
import com.jean.moviesarchitectcoders.domain.repository.MoviesRepository
import com.jean.moviesarchitectcoders.domain.repository.RegionRepository
import com.jean.moviesarchitectcoders.domain.usecases.GetFavoriteMoviesUseCase
import com.jean.moviesarchitectcoders.domain.usecases.GetMoviesUseCase
import com.jean.moviesarchitectcoders.presentation.viewmodel.MovieViewModel
import com.jean.moviesarchitectcoders.utils.CoroutineTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule

@ExperimentalCoroutinesApi
class MovieViewModelTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private lateinit var remoteDatasource: FakeMovieRemoteDatasource
    private lateinit var localDatasource: FakeMovieLocalDatasource
    private lateinit var moviesRepository: MoviesRepository

    private lateinit var locationDatasource: FakeLocationDatasource
    private lateinit var regionRepository: RegionRepository

    private lateinit var getMovieUseCase: GetMoviesUseCase
    private lateinit var getFavoriteUseCase: GetFavoriteMoviesUseCase

    private lateinit var sut: MovieViewModel

    @Before
    fun setUp() {
        remoteDatasource = FakeMovieRemoteDatasource()
        localDatasource = FakeMovieLocalDatasource()
        moviesRepository = MoviesRepositoryImpl(
            remoteDatasource,
            localDatasource
        )

        locationDatasource = FakeLocationDatasource()
        regionRepository = RegionRepositoryImpl(locationDatasource)

        getMovieUseCase = GetMoviesUseCase(
            moviesRepository,
            regionRepository
        )
        getFavoriteUseCase = GetFavoriteMoviesUseCase(moviesRepository)

        sut = MovieViewModel(
            getFavoriteUseCase,
            getMovieUseCase
        )
    }

    // TODO create viewmodel test cases

}