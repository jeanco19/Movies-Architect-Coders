package com.jean.moviesarchitectcoders.data.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jean.moviesarchitectcoders.data.database.MoviesDatabase
import com.jean.moviesarchitectcoders.data.database.dao.MoviesDao
import com.jean.moviesarchitectcoders.data.utils.Constants.MOVIES_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): RoomDatabase {
        return Room.databaseBuilder(
            context,
            MoviesDatabase::class.java,
            MOVIES_DATABASE
        ).build()
    }

    @Provides
    fun provideFavoriteDao(database: MoviesDatabase): MoviesDao {
        return database.moviesDao()
    }

}