package com.example.twitchtopgames.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object GamesApiService {

    private const val BASE_URL = "https://api.twitch.tv/kraken/"

    private val retrofit: Retrofit by lazy { buildRetrofit() }
    private const val TIMEOUT_IN_SECOND = 10

    val gamesApi: TopGamesApi by lazy { retrofit.create(TopGamesApi::class.java) }

    private fun buildRetrofit(): Retrofit {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(buildOkHttpClient())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    private fun buildOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(ApiKeyInterceptor())
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .readTimeout(TIMEOUT_IN_SECOND.toLong(), TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT_IN_SECOND.toLong(), TimeUnit.SECONDS)
            .build()
    }
}