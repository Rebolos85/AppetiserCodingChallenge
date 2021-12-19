package com.example.codingchallenge.features.description

import com.example.codingchallenge.domain.core.DetailsStorage

sealed class SingleMusicItemState {
    object IDLE: SingleMusicItemState()
    data class SingleMusicItemStorage(val dataState: MutableList<DetailsStorage>) : SingleMusicItemState()
}
