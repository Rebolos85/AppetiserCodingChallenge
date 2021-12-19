package com.example.codingchallenge.utils.mapper.impl

import com.example.codingchallenge.local.features.music.model.MusicLocalEntity
import com.example.codingchallenge.domain.model.Music
import com.example.codingchallenge.utils.mapper.EntityMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MusicCacheImpl @Inject constructor() : EntityMapper<MusicLocalEntity, Music> {
    /**
     * Passing the local cache data to domain model
     * @return music domain - that can be used to display the views
     */
    override fun mapFromEntity(dataResponse: MusicLocalEntity): Music {
        return Music(
            id = dataResponse.id,
            description = dataResponse.description,
            price = dataResponse.price,
            genre = dataResponse.genre,
            maturityText = dataResponse.maturityText ?: "",
            imageURL = dataResponse.imageURL,
            name = dataResponse.name,
            artistName = dataResponse.artistName
        )
    }

    /**
     * @return MusicLocalEntity- passing the data stored from domain model
     * to local cache entity
     */
    override fun mapToEntity(data: Music): MusicLocalEntity {
        return MusicLocalEntity(
            id = data.id,
            description = data.description,
            price = data.price,
            genre = data.genre,
            maturityText = data.maturityText,
            imageURL = data.imageURL,
            name = data.name,
            artistName = data.artistName
        )
    }

    /**
     * Transforming the data coming from local cache to music domain model
     * @return List<Music> - responsible for updating the array adapter
     */
    override fun map(response: List<MusicLocalEntity>): List<Music> {
        return response.map {
            mapFromEntity(dataResponse = it)
        }
    }
}