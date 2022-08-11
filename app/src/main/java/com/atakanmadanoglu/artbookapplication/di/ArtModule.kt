package com.atakanmadanoglu.artbookapplication.di

import com.atakanmadanoglu.artbookapplication.datasource.local.ArtLocalDataSource
import com.atakanmadanoglu.artbookapplication.datasource.local.ArtLocalDataSourceImp
import com.atakanmadanoglu.artbookapplication.datasource.remote.ArtRemoteDataSource
import com.atakanmadanoglu.artbookapplication.datasource.remote.ArtRemoteDataSourceImp
import com.atakanmadanoglu.artbookapplication.repo.ArtRepository
import com.atakanmadanoglu.artbookapplication.repo.ArtRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ArtModule {

    @Singleton
    @Binds
    abstract fun getArtLocalDataSource(
        artLocalDataSourceImp: ArtLocalDataSourceImp
    ): ArtLocalDataSource

    @Singleton
    @Binds
    abstract fun getArtRemoteDataSource(
        artRemoteDataSourceImp: ArtRemoteDataSourceImp
    ): ArtRemoteDataSource

    @Singleton
    @Binds
    abstract fun injectRealRepo(
        artRepositoryImp: ArtRepositoryImp
    ): ArtRepository

}