package com.phatnv.widgets.presentation.screens.authentication

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.phatnv.widgets.data.model.request.AuthenticationEvent
import com.phatnv.widgets.data.model.request.AuthenticationState
import com.phatnv.widgets.data.model.request.AuthenticationViewModel

@Preview
@Composable
fun AuthenticationPage() {
    val viewModel: AuthenticationViewModel = viewModel()

    AuthenticationContent(
        modifier = Modifier.fillMaxWidth(),
        authenticationState = viewModel.uiState.collectAsState().value,
        handleEvent = viewModel::handleEvent
    )
}

@Composable
fun AuthenticationContent(
    modifier: Modifier = Modifier,
    authenticationState: AuthenticationState,
    handleEvent: (event: AuthenticationEvent) -> Unit
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        if (authenticationState.isLoading) {
            CircularProgressIndicator()
        } else {
            AuthenticationForm(
                modifier = Modifier.fillMaxSize(),
                authenticationMode = authenticationState.authenticationMode,
                email = authenticationState.email,
                completedPasswordRequirements = authenticationState.passwordRequirements,
                enableAuthentication = authenticationState.isFormValid(),
                onEmailChanged = { email ->
                    handleEvent(AuthenticationEvent.EmailChanged(email))
                },
                password = authenticationState.password,
                onPasswordChanged = { password ->
                    handleEvent(AuthenticationEvent.PasswordChanged(password))
                },
                onAuthenticate = {
                    handleEvent(AuthenticationEvent.Authenticate)
                },
                onToggleMode = {
                    handleEvent(AuthenticationEvent.ToggleAuthenticationMode)
                }
            )
            authenticationState.error?.let { error ->
                AuthenticationErrorDialog(
                    error = error,
                    dismissError = {
                        handleEvent(
                            AuthenticationEvent.ErrorDismissed
                        )
                    }
                )
            }
        }
    }
}
