package com.example.codingchallenge.utils.mapper.impl

import android.util.Log
import com.example.codingchallenge.local.features.music.model.MusicLocalEntity
import com.example.codingchallenge.network.features.music.model.response.MusicNetworkEntity
import com.example.codingchallenge.utils.mapper.EntityMapper
import javax.inject.Inject

class MusicEntityImpl @Inject constructor() :
    EntityMapper<MusicNetworkEntity, MusicLocalEntity> {
    override fun mapFromEntity(dataResponse: MusicNetworkEntity): MusicLocalEntity {
        return MusicLocalEntity(
            id = dataResponse.id,
            name = dataResponse.name,
            price = dataResponse.price,
            genre = dataResponse.genre,
            description = dataResponse.description ?: "",
            maturityText = dataResponse.maturity,
            imageURL = dataResponse.imageURL,
            artistName = dataResponse.artistName
        )
    }

    override fun mapToEntity(data: MusicLocalEntity): MusicNetworkEntity {
        return MusicNetworkEntity(
            id = data.id,
            description = data.description,
            genre = data.genre,
            imageURL = data.imageURL,
            maturity = data.maturityText ?: "",
            name = data.name,
            price = data.price,
            artistName = data.artistName
        )
    }

    override fun map(response: List<MusicNetworkEntity>): List<MusicLocalEntity> {
        return response.map {
            Log.d("TEST", "Test response ${it.toString()}")
            mapFromEntity(dataResponse = it)
        }
    }


}