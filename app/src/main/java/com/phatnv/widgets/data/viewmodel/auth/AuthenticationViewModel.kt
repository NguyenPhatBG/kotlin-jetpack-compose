package com.phatnv.widgets.data.viewmodel.auth

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.phatnv.widgets.data.enum.AppStorageKey
import com.phatnv.widgets.data.enum.AuthenticationMode
import com.phatnv.widgets.data.enum.PasswordRequirements
import com.phatnv.widgets.data.model.request.LoginRequest
import com.phatnv.widgets.data.repository.AuthRepository
import com.phatnv.widgets.utils.AppDataStoreManager
import com.phatnv.widgets.utils.AppDataStoreManagerSingleton
import com.phatnv.widgets.utils.Functions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AuthenticationViewModel(application: Application) : AndroidViewModel(application) {
    val uiState = MutableStateFlow(AuthenticationState())
    private val authRepository = AuthRepository()

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

            is AuthenticationEvent.FacebookSignIn -> {
                getFacebookSignInClient(authenticationEvent.idToken)
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
            val response = authRepository.postLogin(
                LoginRequest(
                    username = uiState.value.email ?: "",
                    password = uiState.value.password ?: "",
                    device_type = 1,
                    device_token = null
                )
            )

            delay(1000)
            withContext(Dispatchers.Main) {
                uiState.value = uiState.value.copy(
                    isLoading = false,
                )
                if (response.code() == 200 || response.code() == 204) {
                    if (response.body()?.token != null) {
                        navigateToDashboardPage()
                        val appDataStoreManager = AppDataStoreManagerSingleton.getInstance()
                        appDataStoreManager.saveData(AppStorageKey.TOKEN.name, response.body()?.token ?: "")
                    } else {
                        Toast.makeText(context, "Token not found", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    var messageError = Functions.handleError(response)
                    Toast.makeText(context, messageError, Toast.LENGTH_SHORT).show()
                }
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

    private fun getFacebookSignInClient(idToken: String) {
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