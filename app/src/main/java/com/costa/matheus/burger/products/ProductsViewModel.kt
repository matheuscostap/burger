package com.costa.matheus.burger.products

import com.costa.matheus.burger.base.BaseViewModel
import com.costa.matheus.burger.base.ViewState
import com.costa.matheus.domain.entities.Product
import com.costa.matheus.domain.entities.ProductEntity
import com.costa.matheus.domain.entities.ProductPageEntity
import com.costa.matheus.domain.usecases.GetAllProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val useCase: GetAllProductsUseCase
): BaseViewModel() {

    private val privateState = MutableStateFlow<ViewState<List<Product>>>(ViewState.Success(null))
    val state: StateFlow<ViewState<List<Product>>> get() = privateState


    fun getAllProducts() {
        jobs add launch {
            privateState.value = ViewState.Loading
            try {
                val response = useCase.call().await()
                privateState.value = ViewState.Success(response)
            } catch (t: Throwable) {
                privateState.value = ViewState.Error(t, false)
            }
        }
    }
}