package com.example.codingchallenge.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.codingchallenge.local.features.music.dao.ItunesItemDao
import com.example.codingchallenge.local.features.music.model.MusicLocalEntity

@Database(
    entities = [MusicLocalEntity::class],
    version = 6,
    exportSchema = false
)
abstract class ItunesItemDatabase : RoomDatabase() {

    abstract fun itunesDao(): ItunesItemDao

}