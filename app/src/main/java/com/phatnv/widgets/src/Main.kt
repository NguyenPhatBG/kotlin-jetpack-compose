package com.phatnv.widgets.src
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.phatnv.widgets.src.route.NavigationGraph

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    NavigationGraph(navController = navController)
}