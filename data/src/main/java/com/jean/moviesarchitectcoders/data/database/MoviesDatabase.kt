package com.jean.moviesarchitectcoders.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jean.moviesarchitectcoders.data.database.dao.MoviesDao
import com.jean.moviesarchitectcoders.data.database.entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class MoviesDatabase : RoomDatabase() {

    abstract fun moviesDao(): MoviesDao

}