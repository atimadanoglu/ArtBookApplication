package com.atakanmadanoglu.artbookapplication.datasource.local

import androidx.lifecycle.LiveData
import com.atakanmadanoglu.artbookapplication.roomdb.Art

interface ArtLocalDataSource {

    suspend fun insertArt(art: Art)

    suspend fun deleteArt(art: Art)

    fun getArts(): LiveData<List<Art>>

}