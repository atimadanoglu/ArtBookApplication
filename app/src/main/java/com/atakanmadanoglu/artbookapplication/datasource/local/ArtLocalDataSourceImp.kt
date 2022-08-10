package com.atakanmadanoglu.artbookapplication.datasource.local

import androidx.lifecycle.LiveData
import com.atakanmadanoglu.artbookapplication.roomdb.Art
import com.atakanmadanoglu.artbookapplication.roomdb.ArtDao
import javax.inject.Inject

class ArtLocalDataSourceImp @Inject constructor(
    private val dao: ArtDao
): ArtLocalDataSource {

    override suspend fun insertArt(art: Art) = dao.insertArt(art)

    override suspend fun deleteArt(art: Art) = dao.deleteArt(art)

    override fun getArts(): LiveData<List<Art>> = dao.observeArts()

}