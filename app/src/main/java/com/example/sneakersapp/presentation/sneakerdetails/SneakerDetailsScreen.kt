package com.example.sneakersapp.presentation.sneakerdetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.example.sneakersapp.presentation.sneakerdetails.components.EmptyDetailScreen

@Composable
fun SneakerDetailScreen(
    id : Int,
    viewModel: SneakerDetailsViewModel = hiltViewModel()
) {
    if(id == -1) { //because if hardcoded data do not have id
        EmptyDetailScreen()
    } else {
        val state = viewModel.state.value
        LaunchedEffect(true){
            viewModel.getSneakerDetailsById(id)
        }
        SneakerDetailsView(state)  
    }
}

@Composable
fun SneakerDetailsView(state: SneakerDetailsState) = if (state.isLoading.value) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(color = Color.Red)
    }
} else {
    Column( modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())
        .padding(16.dp),) {
        if(state.sneakerDetails == null) {
            EmptyDetailScreen()
        } else {
            val painter = rememberImagePainter(data = state.sneakerDetails.value?.original_picture_url)
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painter,
                    contentDescription = "Sneaker Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.height(400.dp)
                )
            }

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    text = state.sneakerDetails.value?.name ?: "",
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    text = "$ ${state.sneakerDetails.value?.retail_price_cents}",
                    modifier = Modifier
                        .weight(1f),
                    textAlign = TextAlign.End,
                    style = MaterialTheme.typography.headlineSmall
                )
            }
            
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = "${state.sneakerDetails.value?.details}", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            }

        }
    }
}