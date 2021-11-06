package com.costa.matheus.burger.products

import com.costa.matheus.burger.base.BaseViewModel
import com.costa.matheus.burger.base.ViewState
import com.costa.matheus.domain.entities.DayOfferEntity
import com.costa.matheus.domain.entities.Product
import com.costa.matheus.domain.usecases.GetAllProductsUseCase
import com.costa.matheus.domain.usecases.GetDayOfferUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val getAllProductsUseCase: GetAllProductsUseCase,
    private val getDayOfferUseCase: GetDayOfferUseCase
): BaseViewModel() {

    private val allProductsPrivateState = MutableStateFlow<ViewState<List<Product>>>(ViewState.Success(null))
    val allProductsState: StateFlow<ViewState<List<Product>>> get() = allProductsPrivateState

    private val dayOfferPrivateState = MutableStateFlow<ViewState<DayOfferEntity>>(ViewState.Success(null))
    val dayOfferState: StateFlow<ViewState<DayOfferEntity>> get() = dayOfferPrivateState


    fun getAllProducts() {
        jobs add launch {
            allProductsPrivateState.value = ViewState.Loading
            try {
                val response = getAllProductsUseCase.call().await()
                allProductsPrivateState.value = ViewState.Success(response)
            } catch (t: Throwable) {
                allProductsPrivateState.value = ViewState.Error(t, false)
            }
        }
    }

    fun getDayOffer() {
        jobs add launch {
            dayOfferPrivateState.value = ViewState.Loading
            try {
                val response = getDayOfferUseCase.call().await()
                dayOfferPrivateState.value = ViewState.Success(response)
            } catch (t: Throwable) {
                dayOfferPrivateState.value = ViewState.Error(t, false)
            }
        }
    }
}