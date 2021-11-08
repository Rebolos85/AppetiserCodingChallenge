package com.example.codingchallenge.view.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.codingchallenge.adapter.ItemsDetailsAdapter
import com.example.codingchallenge.databinding.ActivityCointanerDetailsBinding
import com.example.codingchallenge.utils.ImageDisplayer

import com.example.codingchallenge.viewmodel.MainViewModel
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
class ItunesDetailsActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModels()

    @Inject
    lateinit var imageDisplayer: ImageDisplayer

    private lateinit var _binding: ActivityCointanerDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityCointanerDetailsBinding.inflate(layoutInflater)
        val view = _binding.root
        setContentView(view)
        val itemDetailsTask = ItemsDetailsAdapter(imageDisplayer)

        mainViewModel.getTrackNameRequest()
        _binding.apply {
            recycleViewItunesList.apply {
                adapter = itemDetailsTask
                layoutManager =
                    LinearLayoutManager(this@ItunesDetailsActivity, RecyclerView.VERTICAL, false)
                setHasFixedSize(true)
            }
        }

        mainViewModel.allItunesItems.observe(this) {
            // updating new data that was added
            itemDetailsTask.updateItunesData(it)

        }
        // handling click event of the recycle view
        itemDetailsTask.onItemClick = {
            Intent(this, DetailsActivity::class.java).apply {
                putExtra("itunes_item_json", Gson().toJson(it))
                startActivity(this)


            }

        }
        collectNewItems()
    }

    private fun collectNewItems() {
        lifecycleScope.launchWhenCreated {
            mainViewModel.newItemState.collect {
                handleSwipeRefreshState(it)
            }
        }
    }

    private fun handleSwipeRefreshState(newItemState: Boolean) {
        _binding.swipeContainer.setOnRefreshListener {
            when (newItemState) {
                true -> {
                    _binding.swipeContainer.isRefreshing = false
                }
                else -> _binding.swipeContainer.isRefreshing = true
            }
        }
    }


}

