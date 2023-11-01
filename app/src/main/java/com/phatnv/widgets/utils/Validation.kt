package com.phatnv.widgets.utils

class Validation {
    companion object {
        fun isValidEmail(email: String): Boolean {
            val emailRegex = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()
            return email.matches(emailRegex)
        }
    }
}
