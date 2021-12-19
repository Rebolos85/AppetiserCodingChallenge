package com.example.codingchallenge.features.listofmusic

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.example.codingchallenge.adapter.ItemsDetailsAdapter
import com.example.codingchallenge.base.BaseVMActivity
import com.example.codingchallenge.common.extensions.Constants.MUSIC_DETAILS
import com.example.codingchallenge.databinding.ActivityCointanerDetailsBinding
import com.example.codingchallenge.di.scopes.ActivityScope
import com.example.codingchallenge.domain.model.Music
import com.example.codingchallenge.ext.toast
import com.example.codingchallenge.features.description.ItunesSingleDescriptionActivity

import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

/**
 * <h1>display a list of items obtained from a iTunes Search API </h1>
 *Create a master-detail application that contains at least one dependency.
 * This application should display a list of items obtained from a iTunes Search API and
 * show a detailed view of each item.
 * @author - Roberto A Rebolos Jr
 * @param ListOfMusicViewModel- instantiating the viewmodel
 * @param ActivityCointainerDetailsBinding - inflating the layout resouces to be use by this
 * activity
 */
@ActivityScope
class ListOfMusicItunesActivity :
    BaseVMActivity<ListOfMusicViewModel, ActivityCointanerDetailsBinding>() {


    lateinit var itunesDetailsAdapter: ItemsDetailsAdapter

    // inflating the layout resources
    override fun getViewBinding(): ActivityCointanerDetailsBinding =
        ActivityCointanerDetailsBinding.inflate(layoutInflater)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getMusicFromItunesApi()
        setUpVmObservers()
        setUpArrayAdapter()
        itunesDetailsAdapter.onItemClick = {
            launchDetailsActivity(it)
        }

    }


    private fun setUpVmObservers() {
        viewModel.state.observeOn(scheduler.ui())
            .subscribeBy(
                onNext = ::handleState,
                onError = {
                    it.message?.let { serverMessage -> toast(serverMessage) }
                },
                onComplete = {
                }
            ).addTo(compositeDisposable = disposables)
    }

    private fun handleState(state: ListOfDetailsState) {
        when (state) {
            is ListOfDetailsState.Success -> {


                itunesDetailsAdapter.updateItunesData(state.successState)
            }
        }
    }

    // setting up the array adapter
    private fun setUpArrayAdapter() {
        itunesDetailsAdapter =
            ItemsDetailsAdapter(
                imageDisplayer = imageDisplayer,
                binding.swipeContainer,
            )
        binding.apply {
            recycleViewItunesList.apply {
                adapter = itunesDetailsAdapter
                layoutManager =
                    GridLayoutManager(this@ListOfMusicItunesActivity, 2)
            }
        }
    }

    /**
     * @return Intent - calling the ItunesSingleDescription activity and passing
     * the domain model
     */
    private fun launchDetailsActivity(music: Music): Intent {
        val launchDetailsActivity =
            Intent(this, ItunesSingleDescriptionActivity::class.java).apply {
                putExtra(MUSIC_DETAILS, music)
                startActivity(this)
            }
        return launchDetailsActivity
    }


}

