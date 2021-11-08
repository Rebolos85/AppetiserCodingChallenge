package com.example.codingchallenge.data.remote.api

import com.example.codingchallenge.model.responses.ItunesBaseItemData
import retrofit2.Response
import retrofit2.http.*

interface AppleApi {
    // searching three values in the remote via @Query and @GET Http methods
    @GET("search")
    suspend fun getSongList(
        @Query("term") term: String,
        @Query("country") country: String,
        @Query("media") media: String,
    ): Response<ItunesBaseItemData>
}