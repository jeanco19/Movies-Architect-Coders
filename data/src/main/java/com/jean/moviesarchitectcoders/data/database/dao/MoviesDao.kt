package com.jean.moviesarchitectcoders.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jean.moviesarchitectcoders.data.database.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun saveMovie(movie: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveFavorite(movie: MovieEntity)

    @Query("SELECT * FROM movies")
    fun getMovies(): Flow<List<MovieEntity>?>

    @Query("SELECT * FROM movies WHERE isFavorite")
    fun getFavoriteMovies(): Flow<List<MovieEntity>?>

    @Query("SELECT COUNT(id) FROM movies")
    suspend fun movieCount(): Int

    @Query("SELECT * FROM movies WHERE id = :movieId")
    fun getMovieById(movieId: Int): Flow<MovieEntity>

}