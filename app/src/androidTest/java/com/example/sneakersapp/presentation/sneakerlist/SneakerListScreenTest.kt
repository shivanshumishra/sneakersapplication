package com.example.sneakersapp.presentation.sneakerlist

import android.content.Context
import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.test.core.app.ApplicationProvider
import com.example.sneakersapp.MainActivity
import com.example.sneakersapp.R
import com.example.sneakersapp.common.BottomNavItem
import com.example.sneakersapp.common.TestTags.SORTBY_DIALOG_SECTION
import com.example.sneakersapp.di.AppModule
import com.example.sneakersapp.ui.theme.SneakersAppTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(AppModule::class)
class SneakerListScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)


    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setup() {
        hiltRule.inject()
        composeRule.activity.setContent {
            val navController = rememberNavController()
            SneakersAppTheme {
                NavHost(navController = navController, startDestination = BottomNavItem.Home.screen_route ) {
                    composable(BottomNavItem.Home.screen_route) {
                        SneakerListScreen(navController = navController)
                    }
                }
            }
        }
    }

    @Test
    fun clickSortBySection_dialogIsVisible() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        composeRule.onNodeWithTag(SORTBY_DIALOG_SECTION).assertDoesNotExist()
        composeRule.onNodeWithContentDescription(context.getString(R.string.sort_by)).performClick()
        composeRule.onNodeWithTag(SORTBY_DIALOG_SECTION).assertIsDisplayed()
    }

}