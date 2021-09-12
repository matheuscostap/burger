package com.costa.matheus.burger.products

import android.graphics.Color
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class ProductsUI {

    @Composable
    @Preview
    fun buildUI() {
        Scaffold (topBar = { BurgerToolbar() }) {

        }
    }

    @Composable
    private fun BurgerToolbar() {
        TopAppBar (
            title = { Text("Burger App")},
        )
    }
}