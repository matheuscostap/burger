package com.costa.matheus.data.datasource

import com.costa.matheus.data.model.DayOfferModel
import com.costa.matheus.data.model.ProductModel
import com.costa.matheus.data.model.ProductsPageResponse
import retrofit2.http.GET

interface BurgerDataSource {

    @GET("/matheuscostap/burger/master/api.json")
    suspend fun getAllProducts(): ProductsPageResponse

    @GET("/matheuscostap/burger/master/dayoffer.json")
    suspend fun getDayOffer(): DayOfferModel

}