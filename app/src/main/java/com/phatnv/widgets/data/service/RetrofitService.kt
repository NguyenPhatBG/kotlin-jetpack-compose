package com.phatnv.widgets.data.service

import com.phatnv.widgets.data.model.request.LoginRequest
import com.phatnv.widgets.data.model.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface RetrofitService {
    /// auth
    @Headers("Content-Type: application/json")
    @POST("api/login")
    suspend fun postLogin(@Body request: LoginRequest): Response<LoginResponse>
    /// Home
}