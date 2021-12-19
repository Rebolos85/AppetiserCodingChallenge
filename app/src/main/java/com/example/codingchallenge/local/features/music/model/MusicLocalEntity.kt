package com.example.codingchallenge.local.features.music.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.codingchallenge.common.extensions.Constants.ITUNES_TABLE_NAME

// for local entity db data class
@Entity(tableName =ITUNES_TABLE_NAME)
data class MusicLocalEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "trackUrl")
    val imageURL: String,
    @ColumnInfo(name = "price")
    val price: Float,
    @ColumnInfo(name = "genre")
    val genre: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "maturity")
    val maturityText: String?,
    @ColumnInfo(name = "artistName")
    val artistName: String,
)