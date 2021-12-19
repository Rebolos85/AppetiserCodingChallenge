package com.example.codingchallenge.network.api

import com.example.codingchallenge.network.base.responses.BaseResponse
import com.example.codingchallenge.network.features.music.model.response.MusicNetworkEntity
import io.reactivex.Single
import retrofit2.http.*

interface AppleApiServices {
    // searching three values in the remote via @Query and @GET Http methods
    @GET("search")
    fun getSongList(
        @Query("term") term: String,
        @Query("country") country: String,
        @Query("media") media: String,
    ): Single<BaseResponse<List<MusicNetworkEntity>>>

}