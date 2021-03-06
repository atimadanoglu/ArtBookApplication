package com.atakanmadanoglu.artbookapplication.roomdb

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ArtDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArt(art: Art)

    @Delete
    suspend fun deleteArt(art: Art)

    @Query("SELECT * FROM arts_table ORDER BY id DESC")
    fun observeArts(): LiveData<List<Art>>

}