package com.example.codingchallenge.network.features.music
import com.example.codingchallenge.network.api.AppleApiServices
import com.example.codingchallenge.network.base.responses.BaseResponse
import com.example.codingchallenge.network.features.music.model.response.MusicNetworkEntity
import io.reactivex.Single


import javax.inject.Inject

class ItunesRemoteSourceImpl @Inject constructor(private val apiServices: AppleApiServices) :
    ItunesRemoteSource {
    override fun getSongList(
        term: String,
        country: String,
        media: String
    ): Single<BaseResponse<List<MusicNetworkEntity>>> {
        return apiServices.getSongList(
            term = term,
            country = country,
            media = media
        )
    }


}
