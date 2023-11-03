package com.phatnv.widgets.data.model.request

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.phatnv.widgets.data.enum.AuthenticationMode
import com.phatnv.widgets.data.enum.PasswordRequirements
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AuthenticationViewModel(application: Application) : AndroidViewModel(application) {
    val uiState = MutableStateFlow(AuthenticationState())

    private val _navigateToDashboard = MutableLiveData<Boolean>()
    val navigateToDashboard: LiveData<Boolean> = _navigateToDashboard

    private val context by lazy {
        application.applicationContext
    }

    private fun toggleAuthenticationMode() {
        val authenticationMode = uiState.value.authenticationMode
        val newAuthenticationMode = if (authenticationMode == AuthenticationMode.SIGN_IN) {
            AuthenticationMode.SIGN_UP
        } else {
            AuthenticationMode.SIGN_IN
        }
        uiState.value = uiState.value.copy(
            authenticationMode = newAuthenticationMode
        )
    }

    fun handleEvent(authenticationEvent: AuthenticationEvent) {
        when (authenticationEvent) {
            is AuthenticationEvent.ToggleAuthenticationMode -> {
                toggleAuthenticationMode()
            }

            is AuthenticationEvent.EmailChanged -> {
                updateEmail(authenticationEvent.emailAddress)
            }

            is AuthenticationEvent.PasswordChanged -> {
                updatePassword(authenticationEvent.password)
            }

            is AuthenticationEvent.Authenticate -> {
                authenticate()
            }

            is AuthenticationEvent.ErrorDismissed -> {
                dismissError()
            }

            is AuthenticationEvent.GoogleSignIn -> {
                getGoogleSignInClient(authenticationEvent.idToken)
            }
        }
    }


    private fun updateEmail(email: String) {
        uiState.value = uiState.value.copy(
            email = email
        )
    }

    private fun updatePassword(password: String) {
        val requirements = mutableListOf<PasswordRequirements>()
        if (password.length > 7) {
            requirements.add(PasswordRequirements.EIGHT_CHARACTERS)
        }
        if (password.any { it.isUpperCase() }) {
            requirements.add(PasswordRequirements.CAPITAL_LETTER)
        }
        if (password.any { it.isDigit() }) {
            requirements.add(PasswordRequirements.NUMBER)
        }
        uiState.value = uiState.value.copy(
            password = password, passwordRequirements = requirements.toList()
        )
    }

    private fun authenticate() {
        uiState.value = uiState.value.copy(
            isLoading = true
        )
        viewModelScope.launch(Dispatchers.IO) {
            delay(2000L)

            withContext(Dispatchers.Main) {
                uiState.value = uiState.value.copy(
                    isLoading = false,
                )
                navigateToDashboardPage()
            }
        }
    }

    private fun dismissError() {
        uiState.value = uiState.value.copy(
            error = null
        )
    }

    private fun getGoogleSignInClient(idToken: String) {
        uiState.value = uiState.value.copy(
            isLoading = true
        )
        viewModelScope.launch(Dispatchers.IO) {
            delay(2000L)

            withContext(Dispatchers.Main) {
                uiState.value = uiState.value.copy(
                    isLoading = false,
                )
                Toast.makeText(context, "Token: $idToken", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun navigateToDashboardPage() {
        _navigateToDashboard.value = true
    }

    fun doneNavigating() {
        _navigateToDashboard.value = false
    }
}