package com.example.sneakersapp.presentation.sneakerdetails

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.sneakersapp.R
import com.example.sneakersapp.common.BottomNavItem
import com.example.sneakersapp.domain.model.Sneaker
import com.example.sneakersapp.presentation.Screen
import com.example.sneakersapp.presentation.sneakerdetails.components.EmptyDetailScreen
import com.example.sneakersapp.presentation.sneakerdetails.components.SizeItem

@Composable
fun SneakerDetailScreen(
    id: Int,
    navController: NavController,
    viewModel: SneakerDetailsViewModel = hiltViewModel()
) {
    if (id == -1) { //because if hardcoded data do not have id
        EmptyDetailScreen()
    } else {
        val state = viewModel.state.value
        LaunchedEffect(true) {
            viewModel.getSneakerDetailsById(id)
        }
        SneakerDetailsView(state,navController)
    }
}

@Composable
fun SneakerDetailsView(state: SneakerDetailsState,navController:NavController) = if (state.isLoading.value) {
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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back_arrow_foreground),
                contentDescription = "Back Button",
                modifier = Modifier
                    .size(60.dp)
                    .clickable {
                        navController.popBackStack()
                    },
                tint = Color.Red
            )

            Icon(
                painter = painterResource(id = R.drawable.ic_cart_foreground),
                contentDescription = "Cart Button",
                modifier = Modifier
                    .size(60.dp)
                    .clickable {
                        navController.navigate(Screen.CartScreen.route) {
                            launchSingleTop = true
                        }
                    },
                tint = Color.Red
            )
        }
        if (state.sneakerDetails == null) {
            EmptyDetailScreen()
        } else {
            val painter =
                rememberImagePainter(data = state.sneakerDetails.value?.original_picture_url)
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
                Text(
                    text = "${state.sneakerDetails.value?.details}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
            state.sneakerDetails.value?.let {
                Text(
                    text = "Select Size",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                )
                SizeRow(it,state)
            }
            val context = LocalContext.current
            state.sneakerDetails.value?.let {
                if (it.has_stock) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(
                                    RoundedCornerShape(
                                        topStart = 16.dp,
                                        topEnd = 16.dp,
                                        bottomEnd = 16.dp,
                                        bottomStart = 16.dp
                                    )
                                )
                                .background(color = Color.Red)
                                .border(
                                    width = 2.dp,
                                    color = Color.Red
                                )
                                .padding(16.dp)
                                .clickable {
                                    if(state.selectedSize.value != 0.0){
                                        state.onAddToCart(it)
                                        Toast.makeText(context,"Added to cart",Toast.LENGTH_LONG).show()
                                    } else {
                                        Toast.makeText(context,"Please select a size",Toast.LENGTH_LONG).show()
                                    }
                                }
                        ) {
                            Text(
                                text = stringResource(id = R.string.add_to_cart_capital),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth(),
                                color = Color.White
                            )
                        }
                    }
                } else {
                    Text(text = stringResource(id = R.string.out_of_stock))
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SizeRow(sneaker: Sneaker,state: SneakerDetailsState) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(7.dp),
        verticalArrangement = Arrangement.spacedBy(7.dp),
    ) {
//        var currentSelected = remember { mutableStateOf<Double>(-1.0) }
        sneaker.size_range?.forEach {
            it?.let {
                SizeItem(size = it, currentSelectedSize = state.selectedSize.value, onItemSelected = {
                    state.selectedSize.value = it
                })
            }
        }
    }
}
