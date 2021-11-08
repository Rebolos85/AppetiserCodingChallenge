package com.example.codingchallenge.use_case


import com.example.codingchallenge.utils.Resource
import retrofit2.Response

/**
 * Use cases are the entry points to the domain layer.
 *
 * @param <Q> the request type
 * @param <P> the response type
</P></Q> */
abstract class UseCase<Q, P> {

     suspend fun run(requestValues: Q): Resource<P> {
        return executeUseCase(requestValues)
    }

    protected abstract suspend  fun executeUseCase(requestValues: Q): Resource<P>
}