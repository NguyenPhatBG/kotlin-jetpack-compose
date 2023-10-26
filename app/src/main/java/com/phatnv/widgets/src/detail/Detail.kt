package com.phatnv.widgets.src.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.phatnv.widgets.src.components.ButtonCommon
import com.phatnv.widgets.src.route.DetailModel

@Composable
fun DetailPage(
    data : DetailModel,
    popBackStack: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Detail Id = ${data.id}", fontSize = 40.sp)
        Text(text = "Detail Id = ${data.name}", fontSize = 40.sp)
        Spacer(modifier = Modifier.height(5.dp))
        ButtonCommon(title = "BACK TO HOME", onClick = { popBackStack("123") })
    }
}