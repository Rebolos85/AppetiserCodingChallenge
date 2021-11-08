package com.example.codingchallenge.utils

import com.example.codingchallenge.model.DetailsStorage

sealed class ItemDetailsState {
    object IDLE: ItemDetailsState()
    data class ItemDetailsStorage(val dataState: MutableList<DetailsStorage>) : ItemDetailsState()
}
