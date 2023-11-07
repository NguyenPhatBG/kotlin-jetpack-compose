package com.phatnv.widgets.data.service

import com.phatnv.widgets.data.model.request.LoginRequest
import com.phatnv.widgets.data.model.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.DELETE
import retrofit2.http.PATCH
interface RetrofitService {
    /// auth
    @POST("api/login")
    suspend fun postLogin(@Body request: LoginRequest): Response<LoginResponse>
    
    /// Home
}