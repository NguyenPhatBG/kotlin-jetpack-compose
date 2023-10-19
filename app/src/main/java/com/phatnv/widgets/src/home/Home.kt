package com.phatnv.widgets.src.home
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.phatnv.widgets.data.enum.APPROUTES
import com.phatnv.widgets.src.route.NavigationGraphHome

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(authNavHostController: NavHostController) {
    val navController = rememberNavController();
    Scaffold(
    ) {
        NavigationGraphHome(navController = navController)
    }
}

@Composable
fun HomeContent(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "This is Home Page", modifier = Modifier.padding(vertical = 10.dp))
        Button(onClick = {
            navController.navigate(APPROUTES.DETAIL.name)
        }) {
            Text(text = "Click here to see detail")
        }
    }
}