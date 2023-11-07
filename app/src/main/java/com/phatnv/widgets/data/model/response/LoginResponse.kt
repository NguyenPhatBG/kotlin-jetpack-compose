package com.phatnv.widgets.data.model.response
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("access_token")
    val token: String,
    @SerializedName("expires_at")
    val time: String,
    val  message: String?,
)
