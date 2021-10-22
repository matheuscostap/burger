package com.costa.matheus.burger.details

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.costa.matheus.domain.entities.Product


class DetailsActivity : ComponentActivity() {

    lateinit var product: Product


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        product = intent.extras?.getSerializable("product") as Product


        setContent {
            DetailsActivityScreen()
        }
    }

    @Composable
    private fun DetailsActivityScreen() {
        DetailsScreen(product).buildUI {
            super.onBackPressed()
        }
    }
}