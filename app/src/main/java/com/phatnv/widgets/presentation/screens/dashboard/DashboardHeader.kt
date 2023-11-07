package com.phatnv.widgets.presentation.screens.dashboard

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.phatnv.widgets.R
import com.phatnv.widgets.presentation.navigation.NavRoute
import com.phatnv.widgets.theme.AppTheme
import com.phatnv.widgets.utils.AppDataStoreManagerSingleton
import kotlinx.coroutines.launch

@Preview
@Composable
fun DashboardHeader(
    modifier: Modifier = Modifier,
    onDrawerClicked: () -> Unit = {},
    navController: NavController
) {
    val coroutineScope = rememberCoroutineScope()
    Box(modifier.height(150.dp)) {
        Row(
            modifier = Modifier.windowInsetsPadding(insets = WindowInsets.systemBars),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { onDrawerClicked() }) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = stringResource(id = R.string.not_implemented),
                )
            }
            Text(
                stringResource(id = R.string.app_heading),
                modifier = Modifier

                    .padding(top = 8.dp),
                style = AppTheme.typography.titleBarStyle,
                textAlign = TextAlign.Start
            )
            IconButton(onClick = {
                coroutineScope.launch {
                    logout(navController)
                }
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(id = R.string.not_implemented),
                )
            }
        }
    }

}
suspend fun logout(navController: NavController) {
//    navController.popBackStack(NavRoute.Authentication.path, true)
    val appDataStoreManager = AppDataStoreManagerSingleton.getInstance()
    appDataStoreManager.deleteKey("token")
    navController.navigate(NavRoute.Authentication.path)
}

@Preview
@Composable
fun SleepSummary(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                stringResource(id = R.string.average_time_in_bed_heading),
                style = AppTheme.typography.h6,
            )
            Text(
                stringResource(id = R.string.placeholder_text_ave_time),
                style = AppTheme.typography.headingStyle,
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                stringResource(id = R.string.average_sleep_time_heading),
                style = AppTheme.typography.smallHeadingStyle,
            )
            Text(
                stringResource(id = R.string.placeholder_text_ave_time_2),
                style = AppTheme.typography.headingStyle,
            )
        }
    }
}