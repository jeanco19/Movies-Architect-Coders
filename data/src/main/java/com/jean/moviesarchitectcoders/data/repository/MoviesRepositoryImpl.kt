package com.jean.moviesarchitectcoders.data.repository

import com.jean.moviesarchitectcoders.data.datasource.movies.local.MovieLocalDatasource
import com.jean.moviesarchitectcoders.data.datasource.movies.remote.MovieRemoteDataSource
import com.jean.moviesarchitectcoders.domain.models.Movie
import com.jean.moviesarchitectcoders.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val remoteDataSource: MovieRemoteDataSource,
    private val localDatasource: MovieLocalDatasource
) : MoviesRepository {

    override fun getMovies(region: String): Flow<Result<List<Movie>>> {
        return flow {
            try {
                localDatasource.getMovies().collect { localMovies ->
                    if (localMovies.isEmpty()) {
                        remoteDataSource.getNowPlayingMovies(region).onEach { saveFavorite(it) }
                    }
                    emit(Result.success(localMovies))
                }
            } catch (exception: Exception) {
                emit(Result.failure(exception))
            }
        }
    }

    override fun getFavorites(): Flow<Result<List<Movie>>> {
        return flow {
            try {
                localDatasource.getMovies().collect { movies ->
                    emit(Result.success(movies.filter { movie -> movie.isFavorite }))
                }
            } catch (exception: Exception) {
                emit(Result.failure(exception))
            }
        }
    }

    override fun getMovieById(movieId: Int): Flow<Result<Movie>> {
        return flow {
            try {
                localDatasource.getMovieById(movieId).collect { movie ->
                    emit(Result.success(movie))
                }
            } catch (exception: Exception) {
                emit(Result.failure(exception))
            }
        }
    }

    override suspend fun saveFavorite(movie: Movie) {
        val updatedMovie = movie.copy(isFavorite = true)
        localDatasource.saveFavoriteMovie(updatedMovie)
    }

}