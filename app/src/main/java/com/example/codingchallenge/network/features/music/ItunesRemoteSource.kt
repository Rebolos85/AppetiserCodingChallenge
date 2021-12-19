package com.example.codingchallenge.network.features.music
import com.example.codingchallenge.network.base.responses.BaseResponse
import com.example.codingchallenge.network.features.music.model.response.MusicNetworkEntity
import io.reactivex.Single


interface ItunesRemoteSource {
    fun getSongList(
        term: String,
        country: String,
        media: String,
    ): Single<BaseResponse<List<MusicNetworkEntity>>>
}