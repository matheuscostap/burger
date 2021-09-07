package com.costa.matheus.burger.products

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.costa.matheus.burger.R
import com.costa.matheus.burger.base.ViewState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject lateinit var viewModel: ProductsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        observeViewModel()
        viewModel.getAllProducts()
    }

    private fun observeViewModel() {
        GlobalScope.launch {
            viewModel.state.collect { state ->
                when(state) {
                    is ViewState.Loading -> {
                        Log.i("MainActivity", "Loading")
                    }

                    is ViewState.Success -> {
                        Log.i("MainActivity", "Success")
                        state.data?.let {
                            Log.i("MainActivity", "$it")
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