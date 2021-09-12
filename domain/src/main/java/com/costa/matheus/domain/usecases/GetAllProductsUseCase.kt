package com.costa.matheus.domain.usecases

import com.costa.matheus.domain.entities.ProductEntity
import com.costa.matheus.domain.repository.AllProductsRepository
import kotlinx.coroutines.Deferred
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class GetAllProductsUseCase @Inject constructor(
    private val repository: AllProductsRepository){

    suspend fun call() = repository.getProducts()
}