package com.phatnv.widgets.data.service

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Headers

private fun provideInterceptor(): Interceptor {
    return Interceptor { chain ->
        val originalRequest = chain.request()
        val newRequest = originalRequest.newBuilder()
            .header("Content-Type", "application/json")
            .build()
        chain.proceed(newRequest)
    }
}

object RetrofitBuilder {
    private const val BASE_URL = "https://stg-theteam.themansions.vn/"

    private val client = OkHttpClient.Builder()
        .addInterceptor(provideInterceptor())
        .build()

    private fun retrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
    val retrofitService: RetrofitService = retrofit().create(RetrofitService::class.java)
}