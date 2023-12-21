package com.example.sneakersapp.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.sneakersapp.common.BottomNavItem
import com.example.sneakersapp.presentation.cart.CartScreen
import com.example.sneakersapp.presentation.sneakerdetails.SneakerDetailScreen
import com.example.sneakersapp.presentation.sneakerlist.SneakerListScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {

    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            if (currentRoute(navController) != Screen.SneakerDetailScreen.route + "/{id}" && currentRoute(
                    navController = navController) != Screen.CartScreen.route
                ) {
                BottomAppBar {
                    AppBottomNavigation(navController = navController)
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(
                PaddingValues(
                    0.dp,
                    0.dp,
                    0.dp,
                    innerPadding.calculateBottomPadding()
                )
            )
        ) {
            NavHost(navController, startDestination = BottomNavItem.Home.screen_route) {
                composable(BottomNavItem.Home.screen_route) {
                   SneakerListScreen(navController)
                }
                composable(BottomNavItem.Cart.screen_route) {
                    CartScreen(navController)
                }
                composable(Screen.CartScreen.route) {
                    CartScreen(navController)
                }
                composable(Screen.SneakerDetailScreen.route + "/{id}",
                    arguments = listOf(navArgument("id") { type = NavType.IntType })
                    ) {
                    val id = it.arguments?.getInt("id") ?: -1
                    SneakerDetailScreen(id = id,navController)
                }
            }
        }
    }
}

@Composable
public fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}