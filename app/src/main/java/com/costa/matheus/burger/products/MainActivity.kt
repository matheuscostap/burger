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

        observeViewModel()
        viewModel.getAllProducts()
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
                Divider(color = Color.LightGray, thickness = 1.dp, modifier = Modifier.padding(start = 16.dp, end = 16.dp))
            }
        }
    }

    @Composable
    private fun ProductItem(product: ProductEntity) {
        Row(verticalAlignment = Alignment.Top,
            modifier = Modifier.fillMaxWidth()) {
            Image(painter = rememberImagePainter(data = product.image),
                contentDescription = "",
                Modifier
                    .size(100.dp)
                    .weight(1f)
                    .padding(start = 16.dp, top = 16.dp, bottom = 16.dp, end = 8.dp))

            Column (
                Modifier
                    .fillMaxWidth()
                    .weight(3f)
                    .padding(start = 8.dp, top = 16.dp, bottom = 16.dp, end = 16.dp)){
                
                Row(verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()) {

                    Text(text = product.name,
                        fontSize = 16.sp,
                        color = Color.Black,
                        modifier = Modifier.weight(3f)
                    )

                    Text(
                        text = product.price,
                        fontSize = 16.sp,
                        color = Color.Black,
                        modifier = Modifier.weight(1.5f),
                        textAlign = TextAlign.End
                    )
                }

                Text(text = product.description,
                    fontSize = 14.sp,
                    color = Color.Gray)
            }
        }
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