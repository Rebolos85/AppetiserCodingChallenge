package com.example.codingchallenge.local.features.music

import com.example.codingchallenge.local.features.music.dao.ItunesItemDao
import com.example.codingchallenge.local.features.music.model.MusicLocalEntity
import io.reactivex.Single


import javax.inject.Inject

class ItunesLocalImpl @Inject constructor(private val itunesItemDao: ItunesItemDao) :
    ItunesLocalSource {
    override fun insert(musicLocalEntity: List<MusicLocalEntity>) {
       return itunesItemDao.insert(musicLocalEntity = musicLocalEntity)
    }

    override fun getAllMusicItems(): Single<List<MusicLocalEntity>> {
        return itunesItemDao.getAllMusicItems()
    }
}