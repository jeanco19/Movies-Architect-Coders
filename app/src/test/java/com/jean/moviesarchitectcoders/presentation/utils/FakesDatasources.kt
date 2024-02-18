package com.jean.moviesarchitectcoders.presentation.utils

import com.jean.moviesarchitectcoders.data.datasource.location.LocationDatasource
import com.jean.moviesarchitectcoders.data.datasource.movies.local.MovieLocalDatasource
import com.jean.moviesarchitectcoders.data.datasource.movies.remote.MovieRemoteDatasource
import com.jean.moviesarchitectcoders.domain.models.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf

class FakeMovieLocalDatasource : MovieLocalDatasource {

    var moviesInMemory: MutableList<Movie> = mutableListOf()

    override suspend fun hasEmptyList(): Boolean = moviesInMemory.isEmpty()

    override suspend fun saveMovie(movie: Movie) {
        moviesInMemory.add(movie)
    }

    override suspend fun saveFavorite(movie: Movie) {
        moviesInMemory.add(movie)
    }

    override fun getMovies(): Flow<List<Movie>> = flowOf(moviesInMemory)

    override fun getFavoriteMovies(): Flow<List<Movie>> {
        return flowOf(moviesInMemory.filter { it.isFavorite })
    }

    override fun getMovieById(movieId: Int): Flow<Movie> {
        return flowOf(moviesInMemory.first { it.id == movieId })
    }

}

class FakeMovieRemoteDatasource : MovieRemoteDatasource {

    var movies = expectedMovies

    override suspend fun getNowPlayingMovies(region: String): List<Movie> = movies

}

class FakeLocationDatasource : LocationDatasource {

    override suspend fun findLastRegion(): String = "ES"

}