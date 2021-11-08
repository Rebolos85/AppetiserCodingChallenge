package com.example.codingchallenge.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.codingchallenge.model.responses.ItunesDataItem
import com.example.codingchallenge.other.Constants.ITUNES_TABLE_NAME
import kotlinx.coroutines.flow.Flow

@Dao
interface ItunesItemDao {

    // get all data that was inserted
    @Query("SELECT * FROM $ITUNES_TABLE_NAME")
    fun getAllTopicData(): Flow<List<ItunesDataItem>>

    // insert all data coming from remote
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllItunesItemData(listOfTopic: List<ItunesDataItem>)

    // delete all data
    @Query("DELETE FROM $ITUNES_TABLE_NAME")
    suspend fun deleteAllItunesItemData()

}