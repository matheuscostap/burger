package com.costa.matheus.data.datasource

import com.costa.matheus.data.model.ProductModel
import retrofit2.http.GET

interface BurgerDataSource {

    @GET("URL")
    suspend fun getAllProducts(): List<ProductModel>

}