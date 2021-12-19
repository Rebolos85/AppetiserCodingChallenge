package com.example.codingchallenge.use_case



import io.reactivex.Single


/**
 * Use cases are the entry points to the domain layer.
 *
 * @param <Q> the request type
 * @param <P> the response type
</P></Q> */
abstract class SingleUseCase<Q, P> {

    fun run(requestValues: Q): Single<P> {
        return executeUseCase(requestValues)
    }

    protected abstract  fun executeUseCase(requestValues: Q): Single<P>
}