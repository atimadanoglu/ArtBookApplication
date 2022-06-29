package com.atakanmadanoglu.artbookapplication.roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "arts_table")
data class Art(
    var name: String = "",
    var artistName: String = "",
    var year: Int = 0,
    var imageUrl: String = "",
    @PrimaryKey
    var id: Int = 0
)
