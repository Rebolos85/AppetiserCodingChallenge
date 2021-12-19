package com.example.codingchallenge.utils.schedulers

import io.reactivex.CompletableTransformer
import io.reactivex.MaybeTransformer
import io.reactivex.SingleTransformer
import io.reactivex.Scheduler

interface BaseSchedulerProvider {

    fun computation(): Scheduler

    fun io(): Scheduler

    fun ui(): Scheduler

    fun <T> applySchedulers(): SingleTransformer<T, T>

    fun <T> applyMaybeSchedulers(): MaybeTransformer<T, T>

    fun applyCompletableSchedulers(): CompletableTransformer
}
