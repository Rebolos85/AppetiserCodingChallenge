package com.example.codingchallenge.data.features.music
import com.example.codingchallenge.domain.model.Music
import com.example.codingchallenge.local.features.music.model.MusicLocalEntity
import com.example.codingchallenge.network.base.responses.BaseResponse
import com.example.codingchallenge.network.features.music.model.response.MusicNetworkEntity
import io.reactivex.Single

interface MusicRepository {

    fun getAllMusicItemsStoredInDb(): Single<List<MusicLocalEntity>>

    fun getSongList(
        term: String,
        country: String,
        media: String
    ): Single<BaseResponse<List<MusicNetworkEntity>>>

    fun insertMusicItem(musicEntity: List<MusicLocalEntity>)

    fun mapNetworkEntityToLocalEntity(list: List<MusicNetworkEntity>): List<MusicLocalEntity>

    fun mapLocalToDomain(list: List<MusicLocalEntity>): List<Music>
}