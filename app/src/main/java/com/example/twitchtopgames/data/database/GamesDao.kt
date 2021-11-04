package com.example.twitchtopgames.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.twitchtopgames.data.domain.GamesDb

@Dao
interface GamesDao {
    @Query("SELECT * FROM games")
    fun getAll(): List<GamesDb>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(games: List<GamesDb>)
}