package com.example.sneakersapp.presentation.cart

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sneakersapp.R
import com.example.sneakersapp.presentation.cart.component.CartItem
import com.example.sneakersapp.presentation.cart.component.EmptyCartView

@Composable
fun CartScreen(
    navController : NavController,
    viewModel: CartViewModel = hiltViewModel(),
){
    val state = viewModel.state
    if(state.value?.isLoading?.value == true) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(
                color = Color.Red
            )
        }
    } else {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
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
        }
        CartView(state)
    }
}


@Composable
fun CartView(state: MutableState<CartViewState?>) {
    if(state.value?.cartItem?.size == 0) {
        EmptyCartView()
    } else {
        LazyColumn() {
            state.value?.cartItem?.let { cartItems ->
                items(cartItems) { item ->
                    CartItem(
                        sneakerCart = item,
                        removeFromCart = {item ->
                            state.value?.let {
                                it.removeItemFromCart(item)
                            }
                        }
                    )
                }
                item {
                    val total = state.value?.total?.value ?: 0
                    CartSummary(total)
                }
            }
        }
    }
}

@Composable
fun CartSummary(total : Int) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        horizontalArrangement = Arrangement.End
    ) {
        Text(text = "Total : $total", style = MaterialTheme.typography.headlineMedium )
    }
}
