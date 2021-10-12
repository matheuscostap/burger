package com.costa.matheus.burger.products


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.costa.matheus.burger.base.ViewState
import com.costa.matheus.burger.ui.card.DayOfferCard
import com.costa.matheus.burger.ui.card.SpecialProductCard
import com.costa.matheus.burger.ui.itemlist.ProductItem
import com.costa.matheus.burger.ui.itemlist.SectionTitle
import com.costa.matheus.burger.ui.toolbar.BurgerToolbar
import com.costa.matheus.domain.entities.DayOfferEntity
import com.costa.matheus.domain.entities.Product
import com.costa.matheus.domain.entities.ProductEntity
import com.costa.matheus.domain.entities.SpecialProductEntity

class ProductsScreen {

    private val cardsColors = arrayOf(
        Color(0xFF982121),
        Color(0XFFFF611d),
        Color(0xffe32929),
        Color(0xffffb80e),
        Color(0xff813531)
    )

    @Composable
    fun buildUI(dayOffer: ViewState<DayOfferEntity>, allProducts: ViewState<List<Product>>) {
        Scaffold (topBar = { BurgerToolbar() }) {
            ProductList(dayOffer = dayOffer, productList = allProducts)
        }
    }

    @Composable
    private fun ProductList(dayOffer: ViewState<DayOfferEntity>, productList: ViewState<List<Product>>) {
        val specialProducts = mutableListOf<SpecialProductEntity>()
        val allProducts = mutableListOf<ProductEntity>()

        if (productList is ViewState.Success) {
            productList.data?.forEach { product ->
                when(product) {
                    is SpecialProductEntity -> {
                        specialProducts.add(product)
                    }

                    is ProductEntity -> {
                        allProducts.add(product)
                    }
                }
            }
        }
        
        LazyColumn(modifier = Modifier.wrapContentHeight()) {
            item {
                DayOfferCard(dayOffer = dayOffer)
            }

            item {
                Column(modifier = Modifier.fillMaxWidth()) {
                    SectionTitle("Combos")

                    when(productList) {
                        is ViewState.Loading -> {
                            LoadingSpecialProducts()
                        }

                        is ViewState.Success -> {
                            SpecialProductsList(specialProducts)
                        }
                    }
                }
            }

            item {
                SectionTitle("McOfertas")
            }

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

    @Composable
    private fun LoadingSpecialProducts() {
        LazyRow {
            items(count = 2){
                SpecialProductCard(
                    product = null,
                    cardsColors.random(),
                    isLoading = true
                )
            }
        }
    }

    @Composable
    private fun SpecialProductsList(specialProducts: List<SpecialProductEntity>) {
        LazyRow {
            itemsIndexed(specialProducts) { index, product ->
                SpecialProductCard(
                    product = product,
                    cardsColors.random(),
                    isLoading = false
                )
            }
        }
    }

}