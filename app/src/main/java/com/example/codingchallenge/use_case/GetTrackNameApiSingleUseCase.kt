package com.example.codingchallenge.use_case

import com.example.codingchallenge.data.features.music.MusicRepository
import com.example.codingchallenge.domain.core.Empty
import com.example.codingchallenge.network.base.responses.BaseResponse
import com.example.codingchallenge.network.features.music.model.response.MusicNetworkEntity
import io.reactivex.Single
import javax.inject.Inject


open class GetTrackNameApiSingleUseCase @Inject constructor(
    private val itunesRepository: MusicRepository,

    ) : SingleUseCase<Empty, BaseResponse<List<MusicNetworkEntity>>>() {
    override fun executeUseCase(requestValues: Empty): Single<BaseResponse<List<MusicNetworkEntity>>> {
        return itunesRepository.getSongList("star","au","movie")

    }


}


/**
 * This method is use execute the business logic of get all song list
 * and performing the business logic to make the code more cleaner
 * @return Resource - which is a wrapper class that is responsible to
 * distinguish about success and error response and to be used by the viewmodel
 */
//    override fun executeUseCase(requestValues: Empty): Single<BaseResponse<List<MusicNetworkEntity>>> {
//        return itunesRepository.getSongList("star", "au", "movie")
//    }


