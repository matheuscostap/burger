package com.costa.matheus.burger.products


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.costa.matheus.domain.entities.Product
import com.costa.matheus.domain.entities.ProductEntity
import com.costa.matheus.domain.entities.SpecialProductEntity

class ProductsUI {


    @Composable
    fun buildUI(snapshotList: SnapshotStateList<Product>) {
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
    private fun ProductList(productList: List<Product>) {
        val specialProducts = mutableListOf<SpecialProductEntity>()
        val allProducts = mutableListOf<ProductEntity>()

        productList.forEach { product ->
            when(product) {
                is SpecialProductEntity -> {
                    specialProducts.add(product)
                }

                is ProductEntity -> {
                    allProducts.add(product)
                }
            }
        }

        Column {
            LazyRow {
                itemsIndexed(specialProducts) { index, product ->
                    SpecialProductCard(product = product)
                }
            }

            LazyColumn {
                itemsIndexed(allProducts) { index, product ->
                    ProductItem(product = product)
                    Divider(
                        color = Color.LightGray,
                        thickness = 1.dp,
                        modifier = Modifier.padding(start = 16.dp, end = 16.dp)
                    )
                }
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


    @Composable
    private fun SpecialProductCard(product: SpecialProductEntity) {
        Card(
            elevation = 4.dp,
            backgroundColor = Color(0xFFff611d),
            modifier = Modifier
                .width(180.dp)
                .height(240.dp)
                .padding(8.dp)
        ) {

            Column {
                Text(
                    text = product.name,
                    fontSize = 16.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )

                Text(
                    text = product.description,
                    fontSize = 14.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, end = 8.dp)
                )

                Image(painter = rememberImagePainter(data = product.image),
                    contentDescription = "",
                    Modifier
                        .background(color = Color(0xFFFFFFFF))
                        .size(100.dp)
                        .align(Alignment.CenterHorizontally)
                )

                Text(
                    text = product.price,
                    fontSize = 16.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(2.dp)
                )
            }

        }
    }
}