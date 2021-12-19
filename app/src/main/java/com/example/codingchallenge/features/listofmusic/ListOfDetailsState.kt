package com.example.codingchallenge.features.listofmusic

import com.example.codingchallenge.domain.model.Music

sealed class ListOfDetailsState {
    object IDLE : ListOfDetailsState()
    data class Success(val successState: List<Music>) : ListOfDetailsState()
    data class ErrorResponse(val successState: String) : ListOfDetailsState()
    object Complete: ListOfDetailsState()
}