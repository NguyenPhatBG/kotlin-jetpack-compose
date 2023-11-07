package com.phatnv.widgets.data.model.request

data class LoginRequest(
    val username: String,
    val password: String,
    val device_type: Int,
    val device_token: String?,
)
