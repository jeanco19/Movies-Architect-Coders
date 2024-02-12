package com.jean.moviesarchitectcoders.viewmodel

import com.jean.moviesarchitectcoders.data.datasource.location.LocationDatasource
import com.jean.moviesarchitectcoders.data.datasource.movies.local.MovieLocalDatasource
import com.jean.moviesarchitectcoders.data.datasource.movies.remote.MovieRemoteDatasource
import com.jean.moviesarchitectcoders.domain.models.Movie
import com.jean.moviesarchitectcoders.utils.expectedMovies
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf

class FakeMovieLocalDatasource : MovieLocalDatasource {

    val moviesInMemory = MutableStateFlow<List<Movie>>(emptyList())

    override suspend fun saveMovie(movie: Movie) {
        val movies = mutableListOf<Movie>()
        movies.add(movie)
        moviesInMemory.value = movies
    }

    override suspend fun saveFavorite(movie: Movie) {
        val movies = mutableListOf<Movie>()
        movies.add(movie)
        moviesInMemory.value = movies
    }

    override fun getMovies(): Flow<List<Movie>> = flowOf(moviesInMemory.value)

    override fun getMovieById(movieId: Int): Flow<Movie> {
        return flowOf(moviesInMemory.value.first { it.id == movieId })
    }

}

class FakeMovieRemoteDatasource : MovieRemoteDatasource {

    override suspend fun getNowPlayingMovies(region: String): List<Movie> = expectedMovies

}

class FakeLocationDatasource : LocationDatasource {

    override suspend fun findLastRegion(): String? = "ES"

}