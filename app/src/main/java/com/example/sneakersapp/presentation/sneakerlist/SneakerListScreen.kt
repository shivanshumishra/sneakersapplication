package com.example.sneakersapp.presentation.sneakerlist


import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sneakersapp.R
import com.example.sneakersapp.presentation.Screen
import com.example.sneakersapp.presentation.sneakerlist.component.SneakerItem
import com.example.sneakersapp.presentation.sneakerlist.component.SortByDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun SneakerListScreen(
    navController: NavController,
    viewModel: SneakerListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val addToCartSuccess = remember {
        mutableStateOf(false)
    }

    val showDialog = remember { mutableStateOf(false) }
    val selectedSort = remember { mutableStateOf("") }

    val context = LocalContext.current

    LaunchedEffect(key1 = addToCartSuccess.value) {
        if (addToCartSuccess.value) {
            Toast.makeText(context, "Sneaker added to cart", Toast.LENGTH_LONG).show()
            addToCartSuccess.value = false
        }
    }

    LaunchedEffect(key1 = selectedSort.value) {
        viewModel.changeSortDirection(selectedSort.value)
    }


    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        if (showDialog.value) {
            SortByDialog(onStateChange = {
                showDialog.value = it
            }, onOptionSelected = {
                selectedSort.value = it
            }, currentSelected = selectedSort.value)
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "SNEAKERSHIP",
                    color = Color.Red,
                    style = MaterialTheme.typography.headlineLarge,
                )

            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier.clickable {
                        showDialog.value = true
                    }
                ) {
                    Text(
                        text = "Sort By",
                        color = Color.Gray,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                    Icon(
                        painter = painterResource(R.drawable.ic_arrow_down_foreground),
                        contentDescription = stringResource(id = R.string.sort_by),
                        modifier = Modifier.size(50.dp)
                    )
                }
            }
            if (state.isLoading || viewModel.showSortLoader.value) {
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
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2)
                ) {
                    items(state.sneakers) { sneaker ->
                        SneakerItem(
                            sneaker = sneaker,
                            onClick = { sneaker ->
                                navController.navigate(Screen.SneakerDetailScreen.route + "/${sneaker.id}")
                            },
                            addToCart = { sneaker ->
                                CoroutineScope(Dispatchers.IO).launch {
                                    addToCartSuccess.value = viewModel.addSneakerToCart(sneaker)
                                }
                            }
                        )
                    }
                }
            }
        }
        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
    }
}
