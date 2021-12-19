package com.example.codingchallenge.use_case

import com.example.codingchallenge.data.features.music.MusicRepository
import com.example.codingchallenge.local.features.music.model.MusicLocalEntity
import com.example.codingchallenge.domain.model.Music
import javax.inject.Inject

class MapToDomainUseCase @Inject constructor(private val itunesRepository: MusicRepository) {

    fun toDomainModel(list: List<MusicLocalEntity>):List<Music> {
        return itunesRepository.mapLocalToDomain(list)
    }
}