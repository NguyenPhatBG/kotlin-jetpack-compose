package com.phatnv.widgets.src.route

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.phatnv.widgets.data.enum.APPROUTES
import com.phatnv.widgets.src.detail.DetailPage
import com.phatnv.widgets.src.home.HomeContent
import com.phatnv.widgets.src.home.HomePage
import com.phatnv.widgets.src.login.LoginPage

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController, startDestination = APPROUTES.LOGIN
            .name,
    ) {
        composable(APPROUTES.LOGIN.name) {
            LoginPage(navController = navController)
        }
        composable(APPROUTES.HOME.name) {
            HomePage(authNavHostController = navController)
        }
    }
}

@Composable
fun NavigationGraphHome(navController: NavHostController) {
    NavHost(navController = navController, startDestination = APPROUTES.HOME_CONTENT.name){
        composable(APPROUTES.HOME_CONTENT.name) {
            HomeContent(navController)
        }
        composable(APPROUTES.DETAIL.name) {
            DetailPage()
        }

    }
}
