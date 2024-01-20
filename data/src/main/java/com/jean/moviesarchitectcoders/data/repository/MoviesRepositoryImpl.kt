package com.jean.moviesarchitectcoders.data.repository

import com.jean.moviesarchitectcoders.data.datasource.movies.local.MovieLocalDatasource
import com.jean.moviesarchitectcoders.data.datasource.movies.remote.MovieRemoteDataSource
import com.jean.moviesarchitectcoders.data.models.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val remoteDataSource: MovieRemoteDataSource,
    private val localDatasource: MovieLocalDatasource
) : MoviesRepository {

    override fun getMovies(region: String): Flow<List<Movie>> {
        return flow {
            localDatasource.getMovies().collect { movies ->
                if (movies.isEmpty()) getAndSaveRemoteMovies(region)
            }
            localDatasource.getMovies()
        }
    }

    private suspend fun getAndSaveRemoteMovies(region: String) {
        remoteDataSource.getNowPlayingMovies(region).map { movie ->
            localDatasource.saveMovie(movie)
        }
    }

    override suspend fun saveFavorite(movie: Movie) {
        localDatasource.saveFavoriteMovie(movie)
    }

}