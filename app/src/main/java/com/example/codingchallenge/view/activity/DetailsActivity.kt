package com.example.codingchallenge.view.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.codingchallenge.databinding.ActivityDetailsBinding
import com.example.codingchallenge.model.DetailsStorage
import com.example.codingchallenge.model.responses.ItunesDataItem
import com.example.codingchallenge.utils.DetailsTarget
import com.example.codingchallenge.utils.ImageDisplayer
import com.example.codingchallenge.utils.ItemDetailsState
import com.example.codingchallenge.viewmodel.ItunesDetailsViewModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

/**
 * <h1>display a list of items obtained from a iTunes Search API </h1>
 *Create a master-detail application that contains at least one dependency.
 * This application should display a list of items obtained from a iTunes Search API and
 * show a detailed view of each item.
 * @author - Roberto A Rebolos Jr
 */
@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    private val itemDetailsViewModel: ItunesDetailsViewModel by viewModels()
    private lateinit var iTunesItemData: ItunesDataItem

    @Inject
    lateinit var imageDisplayer: ImageDisplayer
    private lateinit var _binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(_binding.root)
        iTunesItemData = Gson().fromJson(intent.extras?.getString("itunes_item_json"),
            ItunesDataItem::class.java)


    }


    override fun onResume() {
        super.onResume()
        itemDetailsViewModel.bind(itunesDataItem = iTunesItemData)

        lifecycleScope.launchWhenResumed {
            itemDetailsViewModel.details.collect {
                when (it) {

                    is ItemDetailsState.ItemDetailsStorage -> {
                        displayDetailsData(it.dataState)
                    }
                }
            }
        }

        lifecycleScope.launchWhenResumed {
            itemDetailsViewModel.albumCover.collect {
                imageDisplayer.displayImage(_binding.albumCover, it)
            }
        }
    }

    /**
     * This method is responsible to display details data
     * via enum to make more descriptive
     */
    private fun displayDetailsData(detailState: MutableList<DetailsStorage>) {
        _binding.apply {
            detailState.forEach {
                val detailTarget = when (it.detailsTarget) {
                    DetailsTarget.TRACKNAME -> trackNameText
                    DetailsTarget.GENRE -> genreText
                    DetailsTarget.ARTIST -> artistText
                    DetailsTarget.PRICE -> priceText

                    DetailsTarget.MATURITY_RATING -> maturityText
                    DetailsTarget.LONG_DESCRIPTION -> longDescriptionText

                }
                detailTarget.text = it.data
            }
        }
    }

}