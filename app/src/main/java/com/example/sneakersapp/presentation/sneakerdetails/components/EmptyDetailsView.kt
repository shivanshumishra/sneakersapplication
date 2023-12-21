package com.example.sneakersapp.presentation.sneakerdetails.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun EmptyDetailScreen() {
    Column (
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Can not find details of the sneaker, Please try after sometime.")
    }
}