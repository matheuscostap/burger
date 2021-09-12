package com.costa.matheus.burger.products

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
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
import com.costa.matheus.domain.entities.ProductEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: ProductsViewModel by viewModels()
    private val snapshotList = SnapshotStateList<ProductEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { buildUI() }
    }


    @Preview
    @Composable
    fun buildUI() {
        Scaffold (topBar = { BurgerToolbar() }) {
            ProductList(productList = snapshotList)
        }
    }

    @Composable
    private fun BurgerToolbar() {
        TopAppBar (
            title = { Text("Burger App") },
        )
    }

    @Composable
    private fun ProductList(productList: List<ProductEntity>) {
        LazyColumn {
            items(productList) { product -> 
                ProductItem(product = product)
            }
        }
    }

    @Composable
    private fun ProductItem(product: ProductEntity) {
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()) {
            Image(painter = rememberImagePainter(data = product.image),
                contentDescription = "",
                Modifier
                    .weight(2f)
                    .padding(start = 16.dp, top = 16.dp, bottom = 16.dp, end = 8.dp))

            Column (
                Modifier
                    .weight(3f)
                    .padding(start = 8.dp, top = 16.dp, bottom = 16.dp, end = 8.dp)){
                Text(text = product.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.Black)

                Text(text = product.description,
                    fontSize = 16.sp,
                    color = Color.Gray)
            }
            Text(
                text = product.price,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.Black,
                modifier = Modifier
                    .padding(start = 8.dp, top = 16.dp, bottom = 16.dp, end = 16.dp)
                    .align(Alignment.Top)
            )
        }
    }

    override fun onResume() {
        super.onResume()
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