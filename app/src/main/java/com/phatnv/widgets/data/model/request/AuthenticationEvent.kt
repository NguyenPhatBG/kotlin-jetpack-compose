package com.phatnv.widgets.data.model.request

sealed class AuthenticationEvent {
    object ToggleAuthenticationMode : AuthenticationEvent()

    class EmailChanged(val emailAddress: String) : AuthenticationEvent()

    class PasswordChanged(val password: String) : AuthenticationEvent()

    object Authenticate : AuthenticationEvent()

    object ErrorDismissed : AuthenticationEvent()

    class GoogleSignIn(val idToken: String): AuthenticationEvent()

    class FacebookSignIn(val idToken: String): AuthenticationEvent()
}
