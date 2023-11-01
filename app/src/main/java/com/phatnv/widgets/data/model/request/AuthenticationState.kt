package com.phatnv.widgets.data.model.request

import com.phatnv.widgets.data.enum.AuthenticationMode
import com.phatnv.widgets.data.enum.PasswordRequirements

data class AuthenticationState(
    val authenticationMode: AuthenticationMode = AuthenticationMode.SIGN_IN,
    val email: String? = null,
    val password: String? = null,
    val passwordRequirements: List<PasswordRequirements> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
) {
    fun isFormValid(): Boolean {
        return password?.isNotEmpty() == true &&
                email?.isNotEmpty() == true &&
                (authenticationMode == AuthenticationMode.SIGN_IN
                        || passwordRequirements.containsAll(
                    PasswordRequirements.values().toList()
                ))
    }
}
