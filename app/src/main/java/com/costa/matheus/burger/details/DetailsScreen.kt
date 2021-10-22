package com.costa.matheus.burger.details

import android.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.costa.matheus.burger.ui.font.Jost
import com.costa.matheus.burger.ui.toolbar.BurgerToolbar
import com.costa.matheus.burger.ui.toolbar.DetailsToolbar
import com.costa.matheus.domain.entities.Product

class DetailsScreen(val product: Product = productPreview) {

    @Preview
    @Composable
    fun buildUI(onClickBack: () -> Unit = {}) {
        Scaffold(
            topBar = { DetailsToolbar(onClickBack) },
            backgroundColor = Color(0XFFFFB08F)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
            ) {
                Row(
                    modifier = Modifier
                        .weight(1f)
                        //.background(Color.Green)
                ) {
                    Image(
                        painter = rememberImagePainter(
                            data = product.image
                        ),
                        contentScale = ContentScale.Fit,
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                    )
                }

                Row(
                    verticalAlignment = Alignment.Bottom,
                    modifier = Modifier
                        .weight(1.5f)
                ) {
                    DetailInfos()
                }
            }
        }
    }

    @Composable
    fun DetailInfos() {
        Box(
            modifier = Modifier
                .wrapContentSize()
                .clip(shape = RoundedCornerShape(
                    topStart = 25.dp,
                    topEnd = 25.dp)
                )
                .background(Color.White)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .wrapContentSize()
            ) {
                Text(
                    fontFamily = Jost,
                    fontWeight = FontWeight.Bold,
                    text = product.name,
                    fontSize = 22.sp,
                    color = Color.Black,
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Text(
                    text = product.description,
                    overflow = TextOverflow.Ellipsis,
                    fontFamily = Jost,
                    fontSize = 16.sp,
                    color = Color.Gray,
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth()
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(top = 48.dp, bottom = 48.dp)
                        .fillMaxWidth()
                ) {
                    IconButton(
                        onClick = { },
                        modifier = Modifier
                            .width(40.dp)
                            .height(40.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "",
                            tint = Color.Black
                        )
                    }

                    Text(
                        fontFamily = Jost,
                        text = "1",
                        fontSize = 22.sp,
                        color = Color.Black
                    )

                    IconButton(
                        onClick = { },
                        modifier = Modifier
                            .width(40.dp)
                            .height(40.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Remove,
                            contentDescription = "",
                            tint = Color.Black
                        )
                    }

                    Text(
                        fontFamily = Jost,
                        text = product.price,
                        fontSize = 20.sp,
                        color = Color.Black,
                        textAlign = TextAlign.End,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }

                Button(
                    shape = RoundedCornerShape(30.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xffd18b87),
                    ),
                    onClick = { },
                    modifier = Modifier
                        .height(50.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        fontFamily = Jost,
                        text = "Adicionar ao Carrinho",
                        fontSize = 18.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}

val productPreview = Product(
    name = "McFritas Grande",
    description = "A batata frita mais famosa do mundo. Deliciosas batatas selecionadas, fritas, crocantes por fora, macias por dentro, douradas, irresistíveis, saborosas, famosas, e todos os outros adjetivos positivos que você quiser dar.",
    image = "https://cache-backend-mcd.mcdonaldscupones.com/media/image/product\$kUXVg4F7/200/200/original?country=br",
    price = "R$ 10.00",
    promotional_price = ""
)