package com.phatnv.widgets.presentation.screens.authentication

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.phatnv.widgets.R
import com.phatnv.widgets.data.enum.AuthenticationMode

@Composable
fun AuthenticationButton(
    modifier: Modifier = Modifier,
    authenticationMode: AuthenticationMode,
    enableAuthentication: Boolean,
    onAuthenticate: () -> Unit,
) {
    Button(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(),
        onClick = { onAuthenticate() },
        enabled = enableAuthentication
    ) {
        Text(
            text = stringResource(
                if (authenticationMode == AuthenticationMode.SIGN_IN) {
                    R.string.action_sign_in
                } else {
                    R.string.action_sign_up
                }
            )
        )
    }
}