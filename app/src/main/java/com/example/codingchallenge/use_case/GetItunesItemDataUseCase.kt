package com.example.codingchallenge.use_case


import com.example.codingchallenge.data.features.music.MusicRepository
import com.example.codingchallenge.local.features.music.model.MusicLocalEntity
import io.reactivex.Single

import javax.inject.Inject


class GetItunesItemDataUseCase @Inject constructor(private val trackRepository: MusicRepository) {

    fun getAllItunesMusic(): Single<List<MusicLocalEntity>> =trackRepository.getAllMusicItemsStoredInDb()


}