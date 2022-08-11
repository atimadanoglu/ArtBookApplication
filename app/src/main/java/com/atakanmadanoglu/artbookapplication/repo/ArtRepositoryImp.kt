package com.atakanmadanoglu.artbookapplication.repo

import androidx.lifecycle.LiveData
import com.atakanmadanoglu.artbookapplication.datasource.local.ArtLocalDataSource
import com.atakanmadanoglu.artbookapplication.datasource.remote.ArtRemoteDataSource
import com.atakanmadanoglu.artbookapplication.model.ImageResponse
import com.atakanmadanoglu.artbookapplication.roomdb.Art
import com.atakanmadanoglu.artbookapplication.util.Resource
import javax.inject.Inject

class ArtRepositoryImp @Inject constructor(
    private val localDataSource: ArtLocalDataSource,
    private val remoteDataSource: ArtRemoteDataSource
): ArtRepository {
    override suspend fun insertArt(art: Art) = localDataSource.insertArt(art)

    override suspend fun deleteArt(art: Art) = localDataSource.deleteArt(art)

    override suspend fun searchImage(
        imageString: String
    ): Resource<ImageResponse> = remoteDataSource.searchImage(imageString)

    override fun getArts(): LiveData<List<Art>> = localDataSource.getArts()
}