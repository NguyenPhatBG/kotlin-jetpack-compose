package com.phatnv.widgets.presentation.screens.dashboard

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
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.phatnv.widgets.R
import com.phatnv.widgets.theme.AppTheme

@Preview
@Composable
fun DashboardHeader(
    modifier: Modifier = Modifier,
    onDrawerClicked: () -> Unit = {}
) {
    Box(modifier.height(150.dp)) {
        Row(modifier = Modifier.windowInsetsPadding(insets = WindowInsets.systemBars)) {
            IconButton(onClick = { onDrawerClicked() }) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = stringResource(id = R.string.not_implemented),
                )
            }
            Text(
                stringResource(id = R.string.app_heading),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                style = AppTheme.typography.titleBarStyle,
                textAlign = TextAlign.Start
            )
        }
    }
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