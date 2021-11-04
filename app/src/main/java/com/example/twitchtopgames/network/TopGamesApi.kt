package com.example.twitchtopgames.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TopGamesApi {
    @GET("games/top")
    fun getProperties(@Query("limit") limit: Int = 50): Call<ResponseGames>
}