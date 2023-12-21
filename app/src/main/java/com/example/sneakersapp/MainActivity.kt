package com.example.sneakersapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.sneakersapp.presentation.MainScreen
import com.example.sneakersapp.ui.theme.SneakersAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SneakersAppTheme {
                MainScreen()
            }
        }
    }
}