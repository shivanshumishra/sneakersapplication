package com.example.sneakersapp.presentation.sneakerdetails.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SizeItem(
    size: Double,
    currentSelectedSize : Double,
    onItemSelected : (Double) -> Unit
) {
    Box(
        modifier = Modifier.clip(
            RoundedCornerShape(
                topStart = 8.dp,
                topEnd = 8.dp,
                bottomEnd = 8.dp,
                bottomStart = 8.dp
            )
        ).border( width = 2.dp,
            color = if(currentSelectedSize == size){
                Color.Red
            } else {
                Color.LightGray
            }
        ).wrapContentWidth().background(
            if(currentSelectedSize == size){
                Color.Red
            } else {
                Color.White
            }
        ).padding(8.dp).clickable {
            onItemSelected(size)
        }
    ) {
        Text(text = "$size", color = if (currentSelectedSize == size) Color.White else Color.Black)
    }
}