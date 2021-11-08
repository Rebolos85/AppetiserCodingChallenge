package com.example.codingchallenge.utils

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T?) : Resource<T>(data = data)
    class Error<T>(data: T?, message: String?) : Resource<T>(data = data, message = message)
}