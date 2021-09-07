package com.costa.matheus.burger.products

import com.costa.matheus.burger.base.BaseViewModel
import com.costa.matheus.burger.base.ViewState
import com.costa.matheus.domain.entities.ProductEntity
import com.costa.matheus.domain.usecases.BaseUseCase
import com.costa.matheus.domain.usecases.GetAllProductsUseCase
import com.costa.matheus.domain.usecases.NoParams
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
class ProductsViewModel @Inject constructor(
    private val useCase: BaseUseCase<NoParams, Deferred<List<ProductEntity>>>
): BaseViewModel() {

    private val privateState = MutableStateFlow<ViewState<List<ProductEntity>>>(ViewState.Success(null))
    val state: StateFlow<ViewState<List<ProductEntity>>> get() = privateState


    fun getAllProducts() {
        jobs add launch {
            privateState.value = ViewState.Loading
            try {
                val response = useCase.call(NoParams()).await()
                privateState.value = ViewState.Success(response)
            } catch (t: Throwable) {
                privateState.value = ViewState.Error(t, false)
            }
        }
    }
}