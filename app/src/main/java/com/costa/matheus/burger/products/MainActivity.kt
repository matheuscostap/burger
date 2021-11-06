package com.costa.matheus.burger.products

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.costa.matheus.burger.base.ViewState
import com.costa.matheus.burger.details.DetailsActivity
import com.costa.matheus.domain.entities.Product
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: ProductsViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { MainActivityScreen(viewModel) }

        viewModel.getAllProducts()
        viewModel.getDayOffer()
    }

    @Composable
    private fun MainActivityScreen(viewModel: ProductsViewModel) {
        val dayOfferState = viewModel.dayOfferState.collectAsState().value
        val allProductsState = viewModel.allProductsState.collectAsState().value

        ProductsScreen(
            dayOfferState = dayOfferState,
            allProductsState = allProductsState,
            onEvent = { event ->
                handleEvent(event)
            }
        )
    }

    private fun handleEvent(event: ProductsScreenEvent) {
        when(event) {
            is OnProductClick -> {
                val intent = Intent(this, DetailsActivity::class.java)
                intent.putExtra("product", event.product)
                startActivity(intent)
            }
        }
    }

}