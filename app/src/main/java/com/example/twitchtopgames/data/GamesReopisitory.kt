package com.example.twitchtopgames.data

import com.example.twitchtopgames.Executors
import com.example.twitchtopgames.data.database.GamesDatabase
import com.example.twitchtopgames.data.domain.Games
import com.example.twitchtopgames.data.domain.GamesDb
import com.example.twitchtopgames.data.domain.ResultListener
import com.example.twitchtopgames.data.domain.mappers.Mapper
import com.example.twitchtopgames.network.ResponseGames
import com.example.twitchtopgames.network.TopGamesApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface GamesRepository {
    fun getGames(
        forceUpdate: Boolean,
        resultListener: ResultListener<List<GamesDb>>
    )
}

internal class GamesRepositoryImpl(
    private val api: TopGamesApi,
    private val gamesMapper: Mapper<Games, GamesDb>,
    private val database: GamesDatabase,
    private val executors: Executors
) : GamesRepository {

    override fun getGames(
        forceUpdate: Boolean,
        resultListener: ResultListener<List<GamesDb>>
    ) {
        if (forceUpdate) {
            getFromRemote(resultListener)
        } else {
            executors.background.execute {
                val dbGames: List<GamesDb> = database.gamesDao().getAll()

                if (dbGames.isNotEmpty()) {
                    resultListener.onSuccess(dbGames)
                } else {
                    getFromRemote(resultListener)
                }
            }
        }
    }

    private fun getFromRemote(resultListener: ResultListener<List<GamesDb>>) =
        api.getProperties()
            .enqueue(object : Callback<ResponseGames> {
                override fun onResponse(
                    call: Call<ResponseGames>,
                    response: Response<ResponseGames>
                ) {
                    val results = response.body()?.top
                    if (response.isSuccessful && !results.isNullOrEmpty()) {
                        val games = results.map { resultItem ->
                            Games(
                                channels = resultItem?.channels.toString(),
                                viewers = resultItem?.viewers.toString(),
                                name = resultItem?.game?.name.toString(),
                                box = resultItem?.game?.box?.medium.toString()
                            )
                        }.map(gamesMapper::map)
                        resultListener.onSuccess(games)

                        executors.background.execute { database.gamesDao().insertAll(games) }
                    }
                }

                override fun onFailure(call: Call<ResponseGames>, t: Throwable) {
                    resultListener.onError(t)
                }

            })

}