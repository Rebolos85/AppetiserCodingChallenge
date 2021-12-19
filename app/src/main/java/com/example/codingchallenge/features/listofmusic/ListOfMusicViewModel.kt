package com.example.codingchallenge.features.listofmusic


import android.util.Log
import com.example.codingchallenge.base.BaseViewModel
import com.example.codingchallenge.domain.core.Empty
import com.example.codingchallenge.use_case.GetItunesItemDataUseCase
import com.example.codingchallenge.use_case.GetTrackNameApiSingleUseCase
import com.example.codingchallenge.use_case.InsertITunesMusicUseCase
import com.example.codingchallenge.use_case.MapToDomainUseCase
import io.reactivex.Observable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject


class ListOfMusicViewModel @Inject constructor(
    private val getTrackNameApiUseCase: GetTrackNameApiSingleUseCase,
    private val getItunesItemDataUseCase: GetItunesItemDataUseCase,
    private val insertITunesMusicUseCase: InsertITunesMusicUseCase,
    private val mapToDomainUseCase: MapToDomainUseCase
) : BaseViewModel() {

    private val _state by lazy {
        PublishSubject.create<ListOfDetailsState>()
    }
    val state: Observable<ListOfDetailsState> = _state

    /**
     * get the music details coming from itunes remote webservices
     */
    fun getMusicFromItunesApi() {
        getTrackNameApiUseCase.run(requestValues = Empty())
            .subscribeOn(schedulerProvider.io())
            .flatMap {
                insertITunesMusicUseCase.insertItunesMusic(it.results)
                getItunesItemDataUseCase.getAllItunesMusic()
            }
            .filter {
                it.isNotEmpty()
            }
            .observeOn(schedulerProvider.ui())
            .subscribeBy(
                onError = {
                    _state.onError(it.fillInStackTrace())
                },
                onSuccess = {
                    Log.d("TEST", "Size of the local entity $it.size")
                    val listOfMusic = mapToDomainUseCase.toDomainModel(it)
                    _state.onNext(ListOfDetailsState.Success(listOfMusic))

                },
                onComplete = {
                    _state.onNext(ListOfDetailsState.Complete)
                }).addTo(disposables)



    }


}



