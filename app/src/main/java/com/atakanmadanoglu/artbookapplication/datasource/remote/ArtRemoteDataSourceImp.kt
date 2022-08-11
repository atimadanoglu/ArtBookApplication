package com.atakanmadanoglu.artbookapplication.datasource.remote

import com.atakanmadanoglu.artbookapplication.api.RetrofitAPI
import com.atakanmadanoglu.artbookapplication.model.ImageResponse
import com.atakanmadanoglu.artbookapplication.util.Resource
import javax.inject.Inject

class ArtRemoteDataSourceImp @Inject constructor(
    private val retrofitAPI: RetrofitAPI
): ArtRemoteDataSource {
    override suspend fun searchImage(imageString: String): Resource<ImageResponse> {
        return try {
            val response = retrofitAPI.imageSearch(imageString)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Error", null)
            } else {
                Resource.error("Error", null)
            }
        } catch (e: Exception) {
            Resource.error("No data!", null)
        }
    }
}