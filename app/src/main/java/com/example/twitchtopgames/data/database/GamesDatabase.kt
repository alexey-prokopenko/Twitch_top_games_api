package com.example.twitchtopgames.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.twitchtopgames.data.domain.GamesDb

@Database(
    entities = [
        GamesDb::class
    ],
    version = 2
)
abstract class GamesDatabase : RoomDatabase() {

    abstract fun gamesDao(): GamesDao
}