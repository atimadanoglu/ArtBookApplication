package com.atakanmadanoglu.artbookapplication.datasource.remote

import com.atakanmadanoglu.artbookapplication.model.ImageResponse
import com.atakanmadanoglu.artbookapplication.util.Resource

interface ArtRemoteDataSource {

    suspend fun searchImage(imageString: String): Resource<ImageResponse>

}