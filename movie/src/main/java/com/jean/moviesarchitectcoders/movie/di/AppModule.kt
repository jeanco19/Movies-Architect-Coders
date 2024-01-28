package com.jean.moviesarchitectcoders.movie.di

import android.content.Context
import com.jean.moviesarchitectcoders.movie.utils.PermissionHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(FragmentComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providePermissionHandler(@ApplicationContext context: Context): PermissionHandler {
        return PermissionHandler(context)
    }

}