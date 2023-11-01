package com.phatnv.widgets.presentation.screens.authentication

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.phatnv.widgets.R
import com.phatnv.widgets.data.enum.AuthenticationMode

@Composable
fun AuthenticationTitle(
    modifier: Modifier = Modifier,
    authenticationMode: AuthenticationMode
) {
    Text(
        stringResource(
            if (authenticationMode == AuthenticationMode.SIGN_IN) {
                R.string.label_sign_in_to_account
            } else {
                R.string.label_sign_up_for_account
            }
        ),
        fontSize = 24.sp,
        modifier = modifier,
    )
}