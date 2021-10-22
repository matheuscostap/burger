package com.costa.matheus.burger.ui.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.costa.matheus.burger.ui.font.Jost
import com.costa.matheus.domain.entities.SpecialProductEntity
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.fade
import com.google.accompanist.placeholder.placeholder

@Composable
fun SpecialProductCard(
    product: SpecialProductEntity?,
    color: Color,
    isLoading: Boolean,
    onClick: (SpecialProductEntity) -> Unit = {}) {

    if (isLoading) {
        Card(
            elevation = 4.dp,
            backgroundColor = color,
            modifier = Modifier
                .width(180.dp)
                .height(240.dp)
                .padding(8.dp)
                .placeholder(
                    visible = true,
                    color = Color.Gray,
                    shape = RoundedCornerShape(4.dp),
                    highlight = PlaceholderHighlight.fade(
                        highlightColor = Color.LightGray
                    )
                )
        ){}
    } else {
        Card(
            elevation = 4.dp,
            backgroundColor = color,
            modifier = Modifier
                .clickable(onClick = { product?.let { onClick(it) }})
                .width(180.dp)
                .height(240.dp)
                .padding(8.dp)
        ) {
            product?.let {
                Column {
                    Column (Modifier.weight(5f)) {
                        Text(
                            fontFamily = Jost,
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
                            fontFamily = Jost,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 2,
                            text = product.description,
                            fontSize = 14.sp,
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 8.dp, end = 8.dp)
                        )

                        Image(painter = rememberImagePainter(data = product.image),
                            contentScale = ContentScale.FillBounds,
                            contentDescription = "",
                            modifier = Modifier
                                .size(100.dp)
                                .align(Alignment.CenterHorizontally)
                        )
                    }

                    Text(
                        fontFamily = Jost,
                        text = product.price,
                        fontSize = 16.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(2.dp)
                            .weight(1f)
                    )
                }
            }
        }
    }
}