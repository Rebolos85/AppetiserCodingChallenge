package com.example.codingchallenge.base


import androidx.lifecycle.ViewModel
import com.example.codingchallenge.utils.schedulers.BaseSchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/* The purpose of this class is to define the same attributes and
   methods that can be used by the subclasses
 */

abstract class BaseViewModel : ViewModel() {
    // to basically improve the performance
    open val disposables: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    @Inject
    lateinit var schedulerProvider: BaseSchedulerProvider

    // to prevent having memory leaks
    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}

