package com.phatnv.widgets.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.phatnv.widgets.presentation.screens.detail.DetailPage
import com.phatnv.widgets.presentation.screens.home.HomeScreen

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavRoute.Home.withArgs(NavRoute.Home.params)
    ) {
        composable(
            NavRoute.Home.withArgsFormat(NavRoute.Home.params),
            arguments = listOf(
                navArgument(NavRoute.Home.params) { type = NavType.StringType },
            )
        ) { backStackEntry ->
            val arg = backStackEntry.arguments
            HomeScreen(
                params = arg?.getString(NavRoute.Home.params),
                navigateToDetail = { id, name ->
                    navController.navigate(NavRoute.Detail.withArgs(id.toString(), name))
                }
            )
        }
        composable(
            NavRoute.Detail.withArgsFormat( NavRoute.Detail.id, NavRoute.Detail.name),
            arguments = listOf(
                navArgument(NavRoute.Detail.id) { type = NavType.StringType },
                navArgument(NavRoute.Detail.name) { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val arg = backStackEntry.arguments
            val id = arg?.getString(NavRoute.Detail.id) ?: ""
            val name = arg?.getString(NavRoute.Detail.name) ?: ""
            val data = DetailModel(
                id = id,
                name = name
            )
            DetailPage(
                data = data,
                popBackStack = { params ->
                    navController.navigate(
                        route = NavRoute.Home.withArgs(params),
                    )
                }
            )
        }
    }
}