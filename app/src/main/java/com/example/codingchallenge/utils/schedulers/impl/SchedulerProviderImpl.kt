package com.example.codingchallenge.utils.schedulers.impl

import com.example.codingchallenge.utils.schedulers.BaseSchedulerProvider
import io.reactivex.CompletableTransformer
import io.reactivex.MaybeTransformer
import io.reactivex.Scheduler
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class SchedulerProviderImpl @Inject constructor() : BaseSchedulerProvider {
    override fun computation(): Scheduler {
        return Schedulers.computation()
    }

    override fun io(): Scheduler {
        return Schedulers.io()
    }

    override fun ui(): Scheduler {
        return AndroidSchedulers.mainThread()
    }


        override fun <T> applySchedulers(): SingleTransformer<T, T> {
            TODO("Not yet implemented")
        }

        override fun <T> applyMaybeSchedulers(): MaybeTransformer<T, T> {
            TODO("Not yet implemented")
        }

        override fun applyCompletableSchedulers(): CompletableTransformer {
            TODO("Not yet implemented")
        }
}