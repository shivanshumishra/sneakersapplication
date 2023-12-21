package com.example.sneakersapp.presentation


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.sneakersapp.common.BottomNavItem
import java.nio.file.WatchEvent

@Composable
fun AppBottomNavigation(
    navController: NavHostController
) {
    val bottomNavigationItems = listOf(
        BottomNavItem.Home,
        BottomNavItem.Cart
    )
    var selectedItem by remember { mutableStateOf(0) }
    var currentRoute by remember { mutableStateOf(BottomNavItem.Home.screen_route) }

    NavigationBar(
        containerColor = Color.Red,
        contentColor = Color.White,
        modifier = Modifier.fillMaxWidth().clip(
            RoundedCornerShape(
                topStart = 15.dp,
                topEnd = 15.dp,
                bottomEnd = 0.dp,
                bottomStart = 0.dp
            )
        )
    ) {
        bottomNavigationItems.forEachIndexed { index, item ->
            NavigationBarItem(
                alwaysShowLabel = false,
                icon = {
                    Icon(
                        painterResource(id = item.icon),
                        contentDescription = item.title,
                        modifier = Modifier.size(40.dp),
                        tint = if(selectedItem == index) Color.Black else Color.White
                    )
                },
                label = { Text(item.title, color = Color.Black) },
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    currentRoute = item.screen_route
                    navController.navigate(item.screen_route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}