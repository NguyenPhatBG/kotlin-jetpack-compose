package com.phatnv.widgets.presentation.screens.authentication

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.phatnv.widgets.data.enum.AuthenticationMode
import com.phatnv.widgets.data.enum.PasswordRequirements

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AuthenticationForm(
    modifier: Modifier = Modifier,
    authenticationMode: AuthenticationMode,
    email: String?,
    password: String?,
    completedPasswordRequirements: List<PasswordRequirements>,
    enableAuthentication: Boolean,
    onEmailChanged: (email: String) -> Unit,
    onPasswordChanged: (password: String) -> Unit,
    onAuthenticate: () -> Unit,
    onToggleMode: () -> Unit
) {
    lateinit var googleSignInClient: GoogleSignInClient
    val focusManager = LocalFocusManager.current
    val (emailRef, passwordRef) = FocusRequester.createRefs()

    Column(modifier = modifier.pointerInput(Unit) {
        detectTapGestures(onTap = {
            focusManager.clearFocus()
        })
    }) {
        Spacer(modifier = Modifier.height(32.dp))
        AuthenticationTitle(authenticationMode = authenticationMode)
        Spacer(modifier = Modifier.height(40.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            ),
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                EmailInput(
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(emailRef)
                        .focusProperties {
                            next = passwordRef
                        },
                    email = email ?: "",
                    onEmailChanged = onEmailChanged,
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Next)
                    },
                )
                Spacer(modifier = Modifier.height(16.dp))
                PasswordInput(
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(passwordRef)
                        .focusProperties {
                            previous = emailRef
                        },
                    password = password ?: "",
                    onPasswordChanged = onPasswordChanged,
                    onDone = onAuthenticate,
                )
                AnimatedVisibility(visible = authenticationMode == AuthenticationMode.SIGN_UP) {
                    PasswordRequirements(completedPasswordRequirements)
                }
                Spacer(modifier = Modifier.height(12.dp))
                AuthenticationButton(
                    modifier = Modifier.height(48.dp),
                    authenticationMode = authenticationMode,
                    enableAuthentication = enableAuthentication,
                    onAuthenticate = onAuthenticate,
                )
            }
        }
        ButtonGoogleSignIn(
            onGoogleSignInCompleted = { token -> println(token) },
            onError = {},
            googleSignInClient = googleSignInClient,
        )
        Spacer(modifier = Modifier.weight(1f))
        ToggleAuthenticationMode(modifier = Modifier.fillMaxWidth(),
            authenticationMode = authenticationMode,
            toggleAuthentication = {
                onToggleMode()
            })
    }
}