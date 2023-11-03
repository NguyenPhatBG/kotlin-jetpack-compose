package com.phatnv.widgets.presentation.screens.authentication

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.phatnv.widgets.data.model.request.AuthenticationEvent
import com.phatnv.widgets.data.model.request.AuthenticationState
import com.phatnv.widgets.data.model.request.AuthenticationViewModel
import com.phatnv.widgets.presentation.navigation.NavRoute

@Composable
fun AuthenticationPage(navController: NavController) {
    val viewModel = viewModel<AuthenticationViewModel>()
    val navigateToDashboard by rememberUpdatedState(newValue = viewModel.navigateToDashboard.value)

    // Check the updated state in real-time
    if (navigateToDashboard is Boolean) {
        navController.navigate(NavRoute.Dashboard.path) {
            popUpTo(NavRoute.Authentication.path) { inclusive = true }
        }
        viewModel.doneNavigating()
    }

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
        modifier = modifier, contentAlignment = Alignment.Center
    ) {
        if (authenticationState.isLoading) AuthenticationDialogLoading()

        AuthenticationForm(modifier = Modifier.fillMaxSize(),
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
            },
            onGoogleSignInCompleted = {idToken ->
                handleEvent(AuthenticationEvent.GoogleSignIn(idToken))
            },
            onFacebookSignInCompleted = {idToken ->
                handleEvent(AuthenticationEvent.FacebookSignIn(idToken))
            }
        )
        authenticationState.error?.let { error ->
            AuthenticationErrorDialog(error = error, dismissError = {
                handleEvent(
                    AuthenticationEvent.ErrorDismissed
                )
            })
        }
    }
}

@Preview
@Composable
fun PreviewAuthenticationPage(navController: NavController) {
    AuthenticationPage(navController)
}