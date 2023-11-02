package com.phatnv.widgets.presentation.screens.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun DashboardPage(
    modifier: Modifier = Modifier,
    onDrawerClicked: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .background(Color.White)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Column(modifier = Modifier.background(Color.Yellow)) {
            DashboardHeader(
                modifier = Modifier.fillMaxWidth(),
                onDrawerClicked = onDrawerClicked
            )
            Spacer(modifier = Modifier.height(32.dp))
            SleepSummary(modifier = Modifier.padding(start = 16.dp, end = 16.dp))
        }
        Spacer(modifier = Modifier.height(16.dp))
        var selectedTab by remember {
            mutableStateOf(SleepTab.Week)
        }
        DashboardHeaderTabs(selectedTab = selectedTab, onTabSelected = {})
    }
}