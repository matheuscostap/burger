package com.costa.matheus.burger.ui.itemlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.costa.matheus.domain.entities.ProductEntity
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.fade
import com.google.accompanist.placeholder.placeholder

@Preview
@Composable
fun ProductItemLoading() {
    Row(verticalAlignment = Alignment.Top,
        modifier = Modifier
            .background(color = Color.White)
            .fillMaxWidth()) {
        Spacer(
            modifier = Modifier
                .size(100.dp)
                .weight(1f)
                .padding(start = 16.dp, top = 16.dp, bottom = 16.dp, end = 8.dp)
                .placeholder(
                    visible = true,
                    color = Color.Gray,
                    shape = RoundedCornerShape(4.dp),
                    highlight = PlaceholderHighlight.fade(
                        highlightColor = Color.LightGray
                    )
                )
        )

        Column (
            Modifier
                .fillMaxWidth()
                .weight(3f)
                .padding(start = 8.dp, top = 16.dp, bottom = 16.dp, end = 16.dp)){

            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()) {

                Text(text = "",
                    fontSize = 16.sp,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .weight(3f)
                        .placeholder(
                            visible = true,
                            color = Color.Gray,
                            shape = RoundedCornerShape(4.dp),
                            highlight = PlaceholderHighlight.fade(
                                highlightColor = Color.LightGray
                            )
                        )
                )

                Text(
                    text = "",
                    fontSize = 16.sp,
                    color = Color.Black,
                    modifier = Modifier
                        .weight(1.5f)
                        .placeholder(
                            visible = true,
                            color = Color.Gray,
                            shape = RoundedCornerShape(4.dp),
                            highlight = PlaceholderHighlight.fade(
                                highlightColor = Color.LightGray
                            )
                        )
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth()) {

                Text(text = "",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    modifier = Modifier
                        .fillMaxWidth()
                        .placeholder(
                            visible = true,
                            color = Color.Gray,
                            shape = RoundedCornerShape(4.dp),
                            highlight = PlaceholderHighlight.fade(
                                highlightColor = Color.LightGray
                            )
                        )
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth()) {

                Text(text = "",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    modifier = Modifier
                        .fillMaxWidth()
                        .placeholder(
                            visible = true,
                            color = Color.Gray,
                            shape = RoundedCornerShape(4.dp),
                            highlight = PlaceholderHighlight.fade(
                                highlightColor = Color.LightGray
                            )
                        )
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth()) {

                Text(text = "",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    modifier = Modifier
                        .fillMaxWidth()
                        .placeholder(
                            visible = true,
                            color = Color.Gray,
                            shape = RoundedCornerShape(4.dp),
                            highlight = PlaceholderHighlight.fade(
                                highlightColor = Color.LightGray
                            )
                        )
                )
            }
        }
    }
}