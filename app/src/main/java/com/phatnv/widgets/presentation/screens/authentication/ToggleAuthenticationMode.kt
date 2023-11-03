package com.phatnv.widgets.presentation.screens.authentication

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.phatnv.widgets.R
import com.phatnv.widgets.data.enum.AuthenticationMode

@Composable
fun ToggleAuthenticationMode(
    modifier: Modifier = Modifier,
    authenticationMode: AuthenticationMode,
    toggleAuthentication: () -> Unit
) {
    Surface(
        modifier = modifier.padding(top = 16.dp),
        shadowElevation = 8.dp
    ) {
        TextButton(
            shape = RoundedCornerShape(0.dp),
            onClick = { toggleAuthentication() }
        ) {
            Text(
                stringResource(
                    if (authenticationMode == AuthenticationMode.SIGN_IN) {
                        R.string.action_need_account
                    } else {
                        R.string.action_already_have_account
                    }
                )
            )
        }
    }
}