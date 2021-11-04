package com.example.twitchtopgames.data.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "games")
class GamesDb(

    @PrimaryKey
    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "viewers")
    val viewers: String,

    @ColumnInfo(name = "channels")
    val channels: String,

    @ColumnInfo(name = "box")
    val box: String
)