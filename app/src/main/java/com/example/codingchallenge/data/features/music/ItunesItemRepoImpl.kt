package com.example.codingchallenge.data.features.music
import com.example.codingchallenge.local.features.music.model.MusicLocalEntity
import com.example.codingchallenge.domain.model.Music
import com.example.codingchallenge.local.features.music.ItunesLocalSource
import com.example.codingchallenge.network.base.responses.BaseResponse
import com.example.codingchallenge.network.features.music.ItunesRemoteSource
import com.example.codingchallenge.network.features.music.model.response.MusicNetworkEntity
import com.example.codingchallenge.utils.mapper.EntityMapper
import io.reactivex.Single


import javax.inject.Inject

class ItunesItemRepoImpl @Inject constructor(
    private val itemDao: ItunesLocalSource,
    private val itunesRemoteSource: ItunesRemoteSource,
    private val mapper: EntityMapper<MusicNetworkEntity, MusicLocalEntity>,
    private val mapperToDomain: EntityMapper<MusicLocalEntity, Music>
) : MusicRepository {
    override fun getAllMusicItemsStoredInDb(): Single<List<MusicLocalEntity>> {
        return itemDao.getAllMusicItems()
    }

    override fun getSongList(
        term: String,
        country: String,
        media: String
    ): Single<BaseResponse<List<MusicNetworkEntity>>> {
        return itunesRemoteSource.getSongList(
            term = term,
            country = country,
            media = media
        )
    }



    override fun insertMusicItem(musicEntity: List<MusicLocalEntity>) {
        itemDao.insert(musicEntity)

    }

    override fun mapNetworkEntityToLocalEntity(list: List<MusicNetworkEntity>): List<MusicLocalEntity> {
        return mapper.map(response = list)
    }

    override fun mapLocalToDomain(list: List<MusicLocalEntity>): List<Music> {
        return mapperToDomain.map(list)
    }


}