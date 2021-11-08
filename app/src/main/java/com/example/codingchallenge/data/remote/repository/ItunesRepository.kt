package com.example.codingchallenge.data.remote.repository

import com.example.codingchallenge.model.responses.ItunesBaseItemData
import com.example.codingchallenge.model.responses.ItunesDataItem
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface ItunesRepository {

    fun getAllTopicData(): Flow<List<ItunesDataItem>>

    suspend fun insertAllItunesItemData(listOfTopic: List<ItunesDataItem>)

    suspend fun deleteAllItunesItemData()
    suspend fun getSongList(
        trackName: String,
        artwork: String,
        trackPrice: String,
    ): Response<ItunesBaseItemData>
}