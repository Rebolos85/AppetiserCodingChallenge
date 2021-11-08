package com.example.codingchallenge.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.codingchallenge.model.request.Empty
import com.example.codingchallenge.model.responses.ItunesDataItem
import com.example.codingchallenge.use_case.GetItunesItemDataUseCase
import com.example.codingchallenge.use_case.GetTrackNameApiUseCase
import com.example.codingchallenge.use_case.InsertAllItunesItemUseCase
import com.example.codingchallenge.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getTrackNameApiUseCase: GetTrackNameApiUseCase,
    private val insertAllItunesItemUseCase: InsertAllItunesItemUseCase,
    private val getItunesItemDataUseCase: GetItunesItemDataUseCase,
) :
    ViewModel() {
    private val _newItemState = MutableStateFlow<Boolean>(false)
     val newItemState get() = _newItemState
    val allItunesItems = getItunesItemDataUseCase.getAllItemsInItunes().asLiveData()
    fun getTrackNameRequest() {
        viewModelScope.launch(Dispatchers.IO) {
            getTrackNameApiUseCase.run(Empty()).apply {
                when (this) {
                    is Resource.Error -> message
                    is Resource.Success -> data?.let {
                        Log.d("MainActvity", "Viewmodel ${it.results}")
                        saveToDatabase(results = it.results)
                    } ?: run {
                        Log.d("TEST", "No data fuck")
                    }
                }
            }
        }
    }

    private fun saveToDatabase(results: List<ItunesDataItem>) {
        viewModelScope.launch(Dispatchers.IO) {
            insertAllItunesItemUseCase.insertAllItunesItems(listOfTopic = results)
            _newItemState.value = true
        }
    }


}