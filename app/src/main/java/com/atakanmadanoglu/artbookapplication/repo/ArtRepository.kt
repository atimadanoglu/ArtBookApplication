package com.atakanmadanoglu.artbookapplication.repo

import androidx.lifecycle.LiveData
import com.atakanmadanoglu.artbookapplication.api.RetrofitAPI
import com.atakanmadanoglu.artbookapplication.model.ImageResponse
import com.atakanmadanoglu.artbookapplication.roomdb.Art
import com.atakanmadanoglu.artbookapplication.roomdb.ArtDao
import com.atakanmadanoglu.artbookapplication.util.Resource
import javax.inject.Inject

class ArtRepository @Inject constructor(
    private val artDao: ArtDao,
    private val retrofitAPI: RetrofitAPI
): ArtRepositoryInterface {
    override suspend fun insertArt(art: Art) {
        artDao.insertArt(art)
    }

    override suspend fun deleteArt(art: Art) {
        artDao.deleteArt(art)
    }

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

    override fun getArt(): LiveData<List<Art>> {
        return artDao.observeArts()
    }
}