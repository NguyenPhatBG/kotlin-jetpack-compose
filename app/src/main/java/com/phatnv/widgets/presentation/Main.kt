package com.phatnv.widgets.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.phatnv.widgets.presentation.navigation.NavRoute
import com.phatnv.widgets.presentation.navigation.NavigationGraph
import com.phatnv.widgets.utils.AppDataStoreManagerSingleton
import kotlinx.coroutines.runBlocking

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    var startDestination: String
    val appDataStoreManager = AppDataStoreManagerSingleton.getInstance()
    runBlocking {
        val token = appDataStoreManager.getData("token")
        startDestination = if (token.isNotEmpty()) {
            NavRoute.Dashboard.path
        } else {
            NavRoute.Authentication.path
        }
    }
    NavigationGraph(navController = navController, startDestination)

}