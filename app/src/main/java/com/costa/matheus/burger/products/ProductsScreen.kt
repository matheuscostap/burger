package com.costa.matheus.burger.products


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.costa.matheus.burger.base.ViewState
import com.costa.matheus.burger.ui.card.DayOfferCard
import com.costa.matheus.burger.ui.card.SpecialProductCard
import com.costa.matheus.burger.ui.color.cardsColors
import com.costa.matheus.burger.ui.itemlist.ProductItem
import com.costa.matheus.burger.ui.itemlist.ProductItemLoading
import com.costa.matheus.burger.ui.itemlist.SectionTitle
import com.costa.matheus.burger.ui.toolbar.BurgerToolbar
import com.costa.matheus.domain.entities.DayOfferEntity
import com.costa.matheus.domain.entities.Product
import com.costa.matheus.domain.entities.ProductEntity
import com.costa.matheus.domain.entities.SpecialProductEntity

@Composable
fun ProductsScreen(
    dayOfferState: ViewState<DayOfferEntity>,
    allProductsState: ViewState<List<Product>>,
    onEvent: (ProductsScreenEvent) -> Unit
) {
    Scaffold (
        topBar = { BurgerToolbar() }
    ) {
        ProductList(
            dayOfferState = dayOfferState,
            allProductsState = allProductsState,
            onEvent = onEvent
        )
    }
}

@Composable
private fun ProductList(
    dayOfferState: ViewState<DayOfferEntity>,
    allProductsState: ViewState<List<Product>>,
    onEvent: (ProductsScreenEvent) -> Unit
) {
    val specialProducts = mutableListOf<SpecialProductEntity>()
    val allProducts = mutableListOf<ProductEntity>()

    if (allProductsState is ViewState.Success) {
        allProductsState.data?.forEach { product ->
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
            DayOfferCard(dayOffer = dayOfferState)
        }

        item {
            Column(modifier = Modifier.fillMaxWidth()) {
                SectionTitle("Combos")

                when(allProductsState) {
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

        when(allProductsState) {
            is ViewState.Loading -> {
                items(count = 5) {
                    ProductItemLoading()
                    Divider(
                        color = Color.LightGray,
                        thickness = 1.dp,
                        modifier = Modifier.padding(start = 16.dp, end = 16.dp)
                    )
                }
            }

            is ViewState.Success -> {
                itemsIndexed(allProducts) { index, product ->
                    ProductItem(
                        product = product,
                        onClick = {
                            onEvent(OnProductClick(it))
                        }
                    )
                    Divider(
                        color = Color.LightGray,
                        thickness = 1.dp,
                        modifier = Modifier.padding(start = 16.dp, end = 16.dp)
                    )
                }
            }
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
                isLoading = false,
                onClick = { }
            )
        }
    }
}