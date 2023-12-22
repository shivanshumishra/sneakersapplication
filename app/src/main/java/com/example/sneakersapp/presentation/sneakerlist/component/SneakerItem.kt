package com.example.sneakersapp.presentation.sneakerlist.component


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.sneakersapp.R
import com.example.sneakersapp.domain.model.Sneaker


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SneakerItem(
    sneaker:Sneaker,
    onClick :(Sneaker) -> Unit,
    addToCart : (Sneaker) -> Unit,
){
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        ),
        modifier = Modifier.padding(8.dp),
        onClick = {
            onClick(sneaker)
        },
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = Color.Black
        )
    ) {
        val painter =
            rememberImagePainter(data = sneaker.grid_picture_url)
        Column(modifier = Modifier
            .fillMaxSize()) {
            if(sneaker.has_stock){
                Row (
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_add_to_cart_foreground),
                        contentDescription = "Add to cart",
                        modifier = Modifier.size(60.dp).clickable {
                              addToCart(sneaker)
                        },
                        tint = Color.Red
                    )
                }
            }
            Image(painter = painter, contentDescription = "${sneaker.name}", contentScale = ContentScale.Fit, modifier = Modifier.size(200.dp))
            Column (
                modifier = Modifier.padding(16.dp)
            ){
                Text(text = "${sneaker.name}", style = MaterialTheme.typography.bodyMedium, maxLines = 2, overflow = TextOverflow.Ellipsis)
                Text(text = "$ ${sneaker.retail_price_cents}", style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}