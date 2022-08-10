package com.atakanmadanoglu.artbookapplication.repo

import androidx.lifecycle.LiveData
import com.atakanmadanoglu.artbookapplication.model.ImageResponse
import com.atakanmadanoglu.artbookapplication.roomdb.Art
import com.atakanmadanoglu.artbookapplication.util.Resource

interface ArtRepository {

    suspend fun insertArt(art: Art)

    suspend fun deleteArt(art: Art)

    suspend fun searchImage(imageString: String): Resource<ImageResponse>

    fun getArts(): LiveData<List<Art>>

}