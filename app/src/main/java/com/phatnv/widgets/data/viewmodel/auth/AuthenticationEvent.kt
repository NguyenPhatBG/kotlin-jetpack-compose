package com.phatnv.widgets.data.viewmodel.auth

sealed class AuthenticationEvent {
    data object ToggleAuthenticationMode : AuthenticationEvent()

    class EmailChanged(val emailAddress: String) : AuthenticationEvent()

    class PasswordChanged(val password: String) : AuthenticationEvent()

    data object Authenticate : AuthenticationEvent()

    data object ErrorDismissed : AuthenticationEvent()

    class GoogleSignIn(val idToken: String): AuthenticationEvent()

    class FacebookSignIn(val idToken: String): AuthenticationEvent()
}
