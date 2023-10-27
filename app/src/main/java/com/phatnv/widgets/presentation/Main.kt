package com.phatnv.widgets.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.phatnv.widgets.presentation.navigation.NavigationGraph

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    NavigationGraph(navController = navController)
}