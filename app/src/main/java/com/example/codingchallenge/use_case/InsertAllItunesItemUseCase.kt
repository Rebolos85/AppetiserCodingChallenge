package com.example.codingchallenge.use_case

import com.example.codingchallenge.data.remote.repository.ItunesRepository
import com.example.codingchallenge.model.responses.ItunesDataItem
import javax.inject.Inject

class InsertAllItunesItemUseCase @Inject constructor(private val trackItunesRepository: ItunesRepository) {
    // this is to insert the data coming from remote to the room db
    suspend fun insertAllItunesItems(listOfTopic: List<ItunesDataItem>) {
        trackItunesRepository.insertAllItunesItemData(listOfTopic = listOfTopic)
    }

}