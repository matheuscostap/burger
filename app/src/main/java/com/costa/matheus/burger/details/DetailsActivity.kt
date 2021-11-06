package com.costa.matheus.burger.details

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.costa.matheus.domain.entities.Product


class DetailsActivity : ComponentActivity() {

    private val viewModel: DetailsViewModel by viewModels()
    lateinit var product: Product


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        product = intent.extras?.getSerializable("product") as Product

        viewModel.setup(product)

        setContent {
            DetailsActivityScreen()
        }
    }

    @Composable
    private fun DetailsActivityScreen() {
        val orderItemState = viewModel.orderItemState.collectAsState().value

        orderItemState?.let {
            DetailsScreen(
                orderItem = it,
                onEvent = { event ->
                    handleEvent(event)
                }
            )
        }
    }

    private fun handleEvent(event: DetailsScreenEvent) {
        when(event) {
            is OnItemAdded -> {
                viewModel.addOneItem()
            }

            is OnItemRemoved -> {
                viewModel.removeOneItem()
            }

            is OnItemAddedToOrder -> {

            }

            is OnBackPress -> {
                super.onBackPressed()
            }
        }
    }
}