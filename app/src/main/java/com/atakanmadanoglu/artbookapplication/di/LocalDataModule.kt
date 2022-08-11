package com.atakanmadanoglu.artbookapplication.di

import android.content.Context
import androidx.room.Room
import com.atakanmadanoglu.artbookapplication.roomdb.ArtDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataModule {

    @Singleton
    @Provides
    fun injectRoomDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context, ArtDB::class.java, "ArtsDB"
    ).build()

    @Singleton
    @Provides
    fun injectDao(database: ArtDB) = database.artDao()
}