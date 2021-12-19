package com.example.codingchallenge.network.features.music.model.response
import com.google.gson.annotations.SerializedName

// for network data response
data class MusicNetworkEntity(
    @SerializedName("trackId") val id: String,
    @SerializedName("trackName") val name: String,
    @SerializedName("artworkUrl100") val imageURL: String,
    @SerializedName("trackPrice") val price: Float,
    @SerializedName("primaryGenreName") val genre: String,
    @SerializedName("longDescription") val description: String?,
    @SerializedName("maturityText") val maturity: String,
    @SerializedName("artistName") val artistName: String,

    )