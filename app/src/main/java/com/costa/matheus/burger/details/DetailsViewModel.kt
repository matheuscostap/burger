package com.costa.matheus.burger.details

import com.costa.matheus.burger.base.BaseViewModel
import com.costa.matheus.domain.entities.OrderItem
import com.costa.matheus.domain.entities.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(): BaseViewModel() {

    private val orderItemPrivateState = MutableStateFlow<OrderItem?>(null)
    val orderItemState: StateFlow<OrderItem?> get() = orderItemPrivateState

    fun setup(product: Product) {
        orderItemPrivateState.value = OrderItem(product, 1)
    }

    fun addOneItem() {
        val currentOrderItem = orderItemPrivateState.value

        currentOrderItem?.let { current ->
            val newQuantity = current.quantity + 1

            orderItemPrivateState.value = OrderItem(
                current.product,
                newQuantity
            )
        }
    }

    fun removeOneItem() {
        val currentOrderItem = orderItemPrivateState.value

        currentOrderItem?.let { current ->
            if (current.quantity > 1) {
                val newQuantity = current.quantity - 1

                orderItemPrivateState.value = OrderItem(
                    current.product,
                    newQuantity
                )
            }
        }
    }

}