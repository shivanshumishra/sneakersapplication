package com.example.sneakersapp.presentation.cart.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.sneakersapp.domain.model.SneakerCart
import com.example.sneakersapp.R

@Composable
fun CartItem(
    sneakerCart: SneakerCart,
    removeFromCart: (SneakerCart) -> Unit
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        ),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = Color.Black
        )
    ) {
        val painter =
            rememberImagePainter(data = sneakerCart.grid_picture_url)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_delete_foreground),
                contentDescription = "Delete item from cart ${sneakerCart.name}",
                modifier = Modifier
                    .size(40.dp)
                    .clickable {
                    removeFromCart(sneakerCart)
                },
                tint = Color.Red
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painter,
                contentDescription = "${sneakerCart.name} Image",
                modifier = Modifier
                    .padding(end = 6.dp)
                    .weight(1f)
                    .size(200.dp),
                contentScale = ContentScale.Fit
            )
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "${sneakerCart.name}")
                Text(text = "${sneakerCart.details}")
                Text(text = "${sneakerCart.brand_name}")
                Text(text = "${sneakerCart.color}")
                Text(text = "${sneakerCart.release_year}")
                Text(text = "Size ${sneakerCart.size}")
                Text(text = "Price : $${sneakerCart.retail_price_cents}")
            }
        }
    }
}