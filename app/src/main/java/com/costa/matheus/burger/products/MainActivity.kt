package com.costa.matheus.burger.products

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.costa.matheus.burger.base.ViewState
import com.costa.matheus.domain.entities.Product
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: ProductsViewModel by viewModels()
    private val snapshotList = SnapshotStateList<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { MainActivityScreen(viewModel) }

        observeViewModel()
        viewModel.getAllProducts()
        viewModel.getDayOffer()
    }

    @Composable
    private fun MainActivityScreen(viewModel: ProductsViewModel) {
        val dayOffer = viewModel.dayOfferState.collectAsState().value
        ProductsScreen().buildUI(dayOffer, snapshotList)
    }


    private fun observeViewModel() {
        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                viewModel.allProductsState.collect { state ->
                    when(state) {
                        is ViewState.Loading -> {
                            Log.i("MainActivity", "Loading")
                        }

                        is ViewState.Success -> {
                            Log.i("MainActivity", "Success")
                            state.data?.let {
                                Log.i("MainActivity", "$it")
                                snapshotList.addAll(it)
                            }
                        }

                        is ViewState.Error -> {
                            Log.i("MainActivity", "Error")
                        }
                    }
                }
            }
        }
    }

}