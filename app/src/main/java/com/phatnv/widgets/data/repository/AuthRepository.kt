package com.phatnv.widgets.data.repository

import com.phatnv.widgets.data.model.request.LoginRequest
import com.phatnv.widgets.data.model.response.LoginResponse
import com.phatnv.widgets.data.service.RetrofitBuilder
import retrofit2.Response

class AuthRepository() {
    private val retrofitService = RetrofitBuilder.retrofitService
    suspend fun postLogin(params: LoginRequest): Response<LoginResponse> {
        return retrofitService.postLogin(params)
    }
}