package com.example.codingchallenge.features.description


import android.util.Log
import com.example.codingchallenge.base.BaseViewModel
import com.example.codingchallenge.domain.core.DetailsStorage
import com.example.codingchallenge.domain.model.Music
import com.example.codingchallenge.utils.ImageDisplayer
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject


class ItunesSingleDescriptionViewModel @Inject constructor(
) : BaseViewModel() {

    private val _state by lazy {
        PublishSubject.create<SingleMusicItemState>()
    }
    val stateSingleMusic: Observable<SingleMusicItemState> = _state
    private val _albumArtworkState = PublishSubject.create<String>()
    val albumArtworkState: Observable<String> = _albumArtworkState
    fun bindMusicDomainData(music: Music) {
        val musicDataList = mutableListOf<DetailsStorage>()
        music.apply {
            musicDataList.add(DetailsStorage(DetailsDescriptionTarget.TRACKNAME, name))
            musicDataList.add(DetailsStorage(DetailsDescriptionTarget.TRACKNAME, name))
            musicDataList.add(DetailsStorage(DetailsDescriptionTarget.GENRE, genre))
            musicDataList.add(DetailsStorage(DetailsDescriptionTarget.ARTIST, artistName))
            musicDataList.add(DetailsStorage(DetailsDescriptionTarget.PRICE, price.toString()))
            musicDataList.add(DetailsStorage(DetailsDescriptionTarget.MATURITY_RATING, maturityText))
            musicDataList.add(DetailsStorage(DetailsDescriptionTarget.LONG_DESCRIPTION, description))
            getMusicAlbumArtwork(musicArtwork = imageURL)
        }
        _state.onNext(SingleMusicItemState.SingleMusicItemStorage(musicDataList))
    }

    /**
     * Displaying the music album artwork
     */
    private fun getMusicAlbumArtwork(
        musicArtwork: String,

        ) {
        Single
            .just(musicArtwork)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribeBy(
                onSuccess = {
                    _albumArtworkState.onNext(it)
                },
                onError = {
                    _albumArtworkState.onError(it)
                }
            ).addTo(disposables)
    }
}
