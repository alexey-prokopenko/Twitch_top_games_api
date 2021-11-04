package com.example.twitchtopgames.data.database

import androidx.room.Room
import com.example.twitchtopgames.App

object DatabaseHolder {

    private const val DATABASE_NAME = "games_database"

    val gamesDatabase: GamesDatabase by lazy { createDatabase() }

    private fun createDatabase(): GamesDatabase = Room.databaseBuilder(
        App.applicationContext(),
        GamesDatabase::class.java,
        DATABASE_NAME
    ).fallbackToDestructiveMigration().build()
}

