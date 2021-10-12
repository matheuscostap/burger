package com.costa.matheus.burger.ui.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.costa.matheus.burger.base.ViewState
import com.costa.matheus.domain.entities.DayOfferEntity
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.fade
import com.google.accompanist.placeholder.material.shimmer
import com.google.accompanist.placeholder.placeholder


@Composable
fun DayOfferCard(dayOffer: ViewState<DayOfferEntity>) {
    when(dayOffer) {
        is ViewState.Loading -> {
            Card(
                elevation = 4.dp,
                modifier = Modifier
                    .height(220.dp)
                    .padding(16.dp)
                    .fillMaxWidth()
                    .placeholder(
                        visible = true,
                        color = Color.Gray,
                        shape = RoundedCornerShape(4.dp),
                        highlight = PlaceholderHighlight.fade(
                            highlightColor = Color.LightGray
                        )
                    )
            ){}
        }

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