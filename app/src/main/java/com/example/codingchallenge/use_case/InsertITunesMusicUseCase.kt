package com.example.codingchallenge.use_case

import com.example.codingchallenge.data.features.music.MusicRepository
import com.example.codingchallenge.network.features.music.model.response.MusicNetworkEntity
import javax.inject.Inject

class InsertITunesMusicUseCase @Inject constructor(private val itunesRepository: MusicRepository) {

    fun insertItunesMusic(networkList: List<MusicNetworkEntity>) {
        val listOfMusicLocal = itunesRepository.mapNetworkEntityToLocalEntity(networkList)
        itunesRepository.insertMusicItem(listOfMusicLocal)

    }
}