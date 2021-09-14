package com.costa.matheus.data.datasource

import com.costa.matheus.data.model.ProductModel
import com.costa.matheus.data.model.ProductsPageResponse
import retrofit2.http.GET

interface BurgerDataSource {

    @GET("/matheuscostap/burger/master/api.json")
    suspend fun getAllProducts(): ProductsPageResponse

}