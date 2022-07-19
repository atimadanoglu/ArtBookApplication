package com.atakanmadanoglu.artbookapplication.api

import com.atakanmadanoglu.artbookapplication.BuildConfig
import com.atakanmadanoglu.artbookapplication.model.ImageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitAPI {

    @GET("/api/")
    suspend fun imageSearch(
        @Query("q") searchQuery: String,
        @Query("key") apiKey: String = BuildConfig.PIXABAY_API_KEY
    ): Response<ImageResponse>

}