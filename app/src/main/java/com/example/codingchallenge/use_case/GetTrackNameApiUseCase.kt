package com.example.codingchallenge.use_case

import android.util.Log
import com.example.codingchallenge.data.remote.repository.ItunesRepository
import com.example.codingchallenge.model.request.Empty
import com.example.codingchallenge.model.responses.ItunesBaseItemData
import com.example.codingchallenge.utils.Resource
import java.lang.Exception
import javax.inject.Inject

class GetTrackNameApiUseCase @Inject constructor(private val itunesRepository: ItunesRepository) :
    UseCase<Empty, ItunesBaseItemData>() {

    /**
     * This method is use execute the business logic of get all song list
     * and performing the business logic to make the code more cleaner
     * @return Resource - which is a wrapper class that is responsible to
     * distinguish about success and error response and to be used by the viewmodel
     */
    override suspend fun executeUseCase(requestValues: Empty): Resource<ItunesBaseItemData> {

        return try {

            val getRequestTrackName =
                itunesRepository.getSongList("star", "au", "movie")

            if (getRequestTrackName.isSuccessful) {
                getRequestTrackName.body()?.let {
                    Log.d("MainActivity", "Success ${it.toString()}")
                    return@let Resource.Success(it)
                } ?: Resource.Error(null, "Something went wrong")
            } else {
                Resource.Error(null, "Something went wrong")
            }
        } catch (error: Exception) {
            Resource.Error(null, "You don't an internet connection")
        }
    }

}