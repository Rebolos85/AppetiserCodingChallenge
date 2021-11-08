package com.example.codingchallenge.viewmodel

import androidx.lifecycle.ViewModel
import com.example.codingchallenge.model.DetailsStorage
import com.example.codingchallenge.model.responses.ItunesDataItem
import com.example.codingchallenge.utils.DetailsTarget
import com.example.codingchallenge.utils.ItemDetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class ItunesDetailsViewModel @Inject constructor() : ViewModel() {

    private val _details = MutableStateFlow<ItemDetailsState>(ItemDetailsState.IDLE)
    private val _albumCover = MutableStateFlow("")
    val albumCover: Flow<String> get() = _albumCover
    val details: Flow<ItemDetailsState> get() = _details
    fun bind(itunesDataItem: ItunesDataItem) {
        val detailsList = mutableListOf<DetailsStorage>()
        itunesDataItem.apply {
            trackName?.let { DetailsStorage(DetailsTarget.TRACKNAME, it) }
                ?.let { detailsList.add(it) }
            primaryGenreName?.let { DetailsStorage(DetailsTarget.GENRE, it) }?.let {
                detailsList.add(it)
            }
            artistName?.let { DetailsStorage(DetailsTarget.ARTIST, it) }?.let {
                detailsList.add(it)
            }
            trackPrice?.let { DetailsStorage(DetailsTarget.PRICE, it) }?.let {
                detailsList.add(it)
            }

            longDescription?.let { DetailsStorage(DetailsTarget.LONG_DESCRIPTION, it) }?.let {
                detailsList.add(it)
            }

            contentAdvisoryRating?.let { DetailsStorage(DetailsTarget.MATURITY_RATING, it) }
                ?.let {
                    detailsList.add(it)
                }
            artworkUrl100?.let {
                _albumCover.value = it
            }
        }
        _details.value = ItemDetailsState.ItemDetailsStorage(detailsList)


    }
}
