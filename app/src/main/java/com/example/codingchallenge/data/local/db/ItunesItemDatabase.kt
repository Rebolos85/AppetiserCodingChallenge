package com.example.codingchallenge.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.codingchallenge.data.local.dao.ItunesItemDao
import com.example.codingchallenge.model.responses.ItunesDataItem

@Database(
    entities = [ItunesDataItem::class],
    version = 3,
    exportSchema = false
)
abstract class ItunesItemDatabase : RoomDatabase() {

    abstract fun itunesDao(): ItunesItemDao
}