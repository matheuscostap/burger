package com.costa.matheus.burger.ui.itemlist

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Preview
@Composable
fun SectionTitle(title: String = "Titulo") {
    Text(text = title,
        fontSize = 20.sp,
        color = Color(0XFFFF611d),
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(16.dp)
    )
}