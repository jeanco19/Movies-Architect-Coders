package com.jean.moviesarchitectcoders.data.repository

import com.jean.moviesarchitectcoders.data.datasource.movies.local.MovieLocalDatasource
import com.jean.moviesarchitectcoders.data.datasource.movies.remote.MovieRemoteDatasource
import com.jean.moviesarchitectcoders.domain.models.Movie
import com.jean.moviesarchitectcoders.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val remoteDataSource: MovieRemoteDatasource,
    private val localDatasource: MovieLocalDatasource
) : MoviesRepository {

    override suspend fun getMovies(region: String): Result<Flow<List<Movie>>> {
        return try {
            if (localDatasource.hasEmptyList()) {
                remoteDataSource.getNowPlayingMovies(region).onEach { movie ->
                    localDatasource.saveFavorite(movie)
                }
            }
            Result.success(localDatasource.getMovies())
        } catch (exception: Exception) {
           Result.failure(exception)
        }
    }

    override suspend fun getFavorites(): Result<Flow<List<Movie>>> {
        return try {
            Result.success(localDatasource.getFavoriteMovies())
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }

    override suspend fun getMovieById(movieId: Int): Result<Flow<Movie>> {
        return try {
            Result.success(localDatasource.getMovieById(movieId))
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }

    override suspend fun saveFavorite(movie: Movie) {
        val updatedMovie = movie.copy(isFavorite = !movie.isFavorite)
        localDatasource.saveFavorite(updatedMovie)
    }

}