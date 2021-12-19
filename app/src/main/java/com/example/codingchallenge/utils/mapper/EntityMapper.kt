package com.example.codingchallenge.utils.mapper

/**
 * Interface for model mappers. It provides helper methods that facilitate
 * retrieving of models from outer data source layers
 *
 * @param <I> either cache,dto model
 * @param <Q> the domain model
 */
interface EntityMapper<I, O> {

    fun mapFromEntity(dataResponse: I): O
    fun mapToEntity(data: O): I

    fun map(response: List<I>): List<O>
}