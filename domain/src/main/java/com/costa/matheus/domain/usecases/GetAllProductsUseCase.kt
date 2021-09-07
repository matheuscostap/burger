package com.costa.matheus.domain.usecases

import com.costa.matheus.domain.entities.ProductEntity
import com.costa.matheus.domain.repository.AllProductsRepository
import kotlinx.coroutines.Deferred


class GetAllProductsUseCase (
    private val repository: AllProductsRepository): BaseUseCase<NoParams, Deferred<List<ProductEntity>>>() {

    override suspend fun call(param: NoParams) = repository.getProducts()
}