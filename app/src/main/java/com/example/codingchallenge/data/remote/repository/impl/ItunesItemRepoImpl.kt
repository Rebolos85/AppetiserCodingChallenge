package com.example.codingchallenge.data.remote.repository.impl

import com.example.codingchallenge.data.local.dao.ItunesItemDao
import com.example.codingchallenge.data.remote.api.AppleApi
import com.example.codingchallenge.data.remote.repository.ItunesRepository
import com.example.codingchallenge.model.responses.ItunesBaseItemData
import com.example.codingchallenge.model.responses.ItunesDataItem
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class ItunesItemRepoImpl @Inject constructor(
    private val itemDao: ItunesItemDao,
    private val appleApi: AppleApi,
) :
    ItunesRepository {
    // get all values stored in dao
    override fun getAllTopicData(): Flow<List<ItunesDataItem>> {
        return itemDao.getAllTopicData()
    }

    // insert all values
    override suspend fun insertAllItunesItemData(listOfTopic: List<ItunesDataItem>) {
        return itemDao.insertAllItunesItemData(listOfTopic)
    }

    //delete all vlaues
    override suspend fun deleteAllItunesItemData() {
        return itemDao.deleteAllItunesItemData()
    }

    // searching data in the remote then returning a response
    override suspend fun getSongList(
        trackName: String,
        artwork: String,
        trackPrice: String,
    ): Response<ItunesBaseItemData> {
        return appleApi.getSongList(term = trackName,
            country = artwork,
            media = trackPrice)
    }


}