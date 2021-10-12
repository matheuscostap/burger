package com.costa.matheus.burger.ui.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.costa.matheus.burger.base.ViewState
import com.costa.matheus.domain.entities.DayOfferEntity



@Composable
fun DayOfferCard(dayOffer: ViewState<DayOfferEntity>) {
    when(dayOffer) {
        is ViewState.Success -> {
            Card(
                elevation = 4.dp,
                modifier = Modifier
                    .height(220.dp)
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Image(painter = rememberImagePainter(data = dayOffer.data?.image),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .fillMaxSize()
                )
            }
        }
    }
}