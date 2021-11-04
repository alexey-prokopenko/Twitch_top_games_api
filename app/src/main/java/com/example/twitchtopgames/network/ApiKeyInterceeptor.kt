package com.example.twitchtopgames.network

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request()
            .url
            .newBuilder()
            .addQueryParameter(API_KEY, NetworkConstants.API_KEY)
            .build()

        val newRequest = chain.request()
            .newBuilder()
            .addHeader("Accept", "application/vnd.twitchtv.v5+json")
            .url(url)
            .build()

        return chain.proceed(newRequest)
    }

    companion object {
        private const val API_KEY = "client_id"
    }
}