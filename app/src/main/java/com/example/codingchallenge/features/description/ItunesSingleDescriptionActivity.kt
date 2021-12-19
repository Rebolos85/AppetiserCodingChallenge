package com.example.codingchallenge.features.description

import android.os.Bundle
import android.util.Log
import com.example.codingchallenge.base.BaseVMActivity
import com.example.codingchallenge.common.extensions.Constants.MUSIC_DETAILS
import com.example.codingchallenge.databinding.ActivityDetailsBinding
import com.example.codingchallenge.di.scopes.ActivityScope
import com.example.codingchallenge.domain.core.DetailsStorage
import com.example.codingchallenge.domain.model.Music
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

/**
 * <h1>display a list of items obtained from a iTunes Search API </h1>
 *Create a master-detail application that contains at least one dependency.
 * This application should display a list of items obtained from a iTunes Search API and
 * show a detailed view of each item.
 * @author - Roberto A Rebolos Jr
 */
//@AndroidEntryPoint
@ActivityScope
class ItunesSingleDescriptionActivity :
    BaseVMActivity<ItunesSingleDescriptionViewModel, ActivityDetailsBinding>() {


    private lateinit var music: Music

    // saving the user state selected artwork when he's navigated away from the screen
    override fun onResume() {
        super.onResume()
        viewModel.bindMusicDomainData(music = music)
        viewModel.stateSingleMusic
            .observeOn(scheduler.ui())
            .subscribeBy(onNext = ::getDetailState,
                onError = {
                    Log.d("ERROR", "Error handling state $it")
                },
                onComplete = {
                    Log.d("ERROR", "Complete na ")
                }
            ).addTo(disposables)


        viewModel.albumArtworkState
            .observeOn(scheduler.ui())
            .subscribeBy(onNext = {
                imageDisplayer.displayImage(binding.artwork, it)
            },
                onError = {
                    Log.d("ERROR", " ERROR FETCHING $it")
                }).addTo(disposables)
    }

    // inflating the layout file of this activity
    override fun getViewBinding(): ActivityDetailsBinding =
        ActivityDetailsBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        music = intent.getParcelableExtra(MUSIC_DETAILS)!!

        viewModel.stateSingleMusic.observeOn(scheduler.ui()).subscribeBy(
            onNext = ::getDetailState,
            onError = {
                Log.d("ERROR", "$it")
            },
            onComplete = {
                Log.d("ERROR", "On complete")
            }).addTo(disposables)


    }


    /**
     * @param detailStateSingleMusic - state for display data in each view
     */
    private fun getDetailState(detailStateSingleMusic: SingleMusicItemState) {
        binding.apply {
            when (detailStateSingleMusic) {
                is SingleMusicItemState.SingleMusicItemStorage -> {
                    displayMusicInformation(detailStateSingleMusic.dataState)

                }
            }

        }
    }

    // displaying user selected music information
    private fun displayMusicInformation(musicDetailsList: MutableList<DetailsStorage>) {
        binding.apply {
            musicDetailsList.forEach {
                val musicDetailsTarget = when (it.detailsDescriptionTarget) {
                    DetailsDescriptionTarget.TRACKNAME -> trackNameText
                    DetailsDescriptionTarget.GENRE -> genreText
                    DetailsDescriptionTarget.ARTIST -> artistText
                    DetailsDescriptionTarget.PRICE -> priceText
                    DetailsDescriptionTarget.MATURITY_RATING -> maturityText
                    DetailsDescriptionTarget.LONG_DESCRIPTION -> longDescriptionText
                }
                musicDetailsTarget.text = it.data
            }
        }
    }
}