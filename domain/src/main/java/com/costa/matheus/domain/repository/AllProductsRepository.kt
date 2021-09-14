package com.costa.matheus.domain.repository

import com.costa.matheus.domain.entities.Product
import com.costa.matheus.domain.entities.ProductEntity
import com.costa.matheus.domain.entities.ProductPageEntity
import kotlinx.coroutines.Deferred


interface AllProductsRepository {
    suspend fun getProducts(): Deferred<List<Product>>
}