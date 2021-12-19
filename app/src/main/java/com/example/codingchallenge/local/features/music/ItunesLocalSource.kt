package com.example.codingchallenge.local.features.music

import com.example.codingchallenge.local.features.music.model.MusicLocalEntity
import io.reactivex.Single


interface ItunesLocalSource {
    fun insert(musicLocalEntity: List<MusicLocalEntity>)

    fun getAllMusicItems(): Single<List<MusicLocalEntity>>
}