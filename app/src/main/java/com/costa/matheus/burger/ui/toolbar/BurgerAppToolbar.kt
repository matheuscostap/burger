package com.costa.matheus.burger.ui.toolbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.costa.matheus.burger.ui.font.Jost

@Preview
@Composable
fun BurgerToolbar() {
    TopAppBar (
        elevation = 0.dp,
        backgroundColor = Color.White,
        content = {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    fontFamily = Jost,
                    text = "\uD83C\uDF54 Burger App",
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    color = Color(0xFF982121),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    )
}