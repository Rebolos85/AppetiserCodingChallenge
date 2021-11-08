package com.example.codingchallenge.use_case

import com.example.codingchallenge.data.remote.repository.ItunesRepository
import javax.inject.Inject


class GetItunesItemDataUseCase @Inject constructor(private val trackRepository: ItunesRepository) {
     // this class is responsible to get all topic data
     fun getAllItemsInItunes() = trackRepository.getAllTopicData()
}