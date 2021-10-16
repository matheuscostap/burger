package com.costa.matheus.burger.ui.itemlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.costa.matheus.domain.entities.ProductEntity

@Composable
fun ProductItem(product: ProductEntity, onClick: (ProductEntity) -> Unit) {
    Row(verticalAlignment = Alignment.Top,
        modifier = Modifier
            .background(color = Color.White)
            .fillMaxWidth()
            .clickable { onClick(product) }
    ) {
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