package com.example.codingchallenge.network.base.responses

/**
 * BaseResponse - for reusable purposes on handling
 * responses from remote
 *
 * @param <T> the response type
 */
data class BaseResponse<T>(
    val resultCount: Int,
    val results: T
)