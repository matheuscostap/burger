package com.costa.matheus.burger.products

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProviders
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.costa.matheus.burger.R
import com.costa.matheus.burger.base.ViewState
import com.costa.matheus.domain.entities.Product
import com.costa.matheus.domain.entities.ProductEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.transform
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: ProductsViewModel by viewModels()
    private val snapshotList = SnapshotStateList<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { ProductsUI().buildUI(snapshotList) }

        observeViewModel()
        viewModel.getAllProducts()
    }

    private fun observeViewModel() {
        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                viewModel.state.collect { state ->
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