package com.jean.moviesarchitectcoders.data.datasource.movies.local

import com.jean.moviesarchitectcoders.data.database.dao.MoviesDao
import com.jean.moviesarchitectcoders.data.mappers.toDomain
import com.jean.moviesarchitectcoders.data.mappers.toEntity
import com.jean.moviesarchitectcoders.domain.models.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRoomDataSource @Inject constructor(
    private val dao: MoviesDao
) : MovieLocalDatasource {

    override suspend fun saveMovie(movie: Movie) {
        dao.saveMovie(movie.toEntity())
    }

    override suspend fun saveFavoriteMovie(movie: Movie) {
        dao.saveFavorite(movie.toEntity())
    }

    override fun getMovies(): Flow<List<Movie>> {
        return dao.getMovies().map { movieEntities -> movieEntities.toDomain() }
    }

    override fun getMovieById(movieId: Int): Flow<Movie> {
        return dao.getMovieById(movieId).map { movieEntity -> movieEntity.toDomain() }
    }

}