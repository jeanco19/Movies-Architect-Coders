package com.jean.moviesarchitectcoders.data.network

import com.jean.moviesarchitectcoders.data.network.models.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApiService {

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(@Query("region") region: String): Response<MoviesResponse>

}