package com.example.twitchtopgames.data.domain.mappers

import com.example.twitchtopgames.data.domain.Games
import com.example.twitchtopgames.data.domain.GamesDb

class GamesMapper : Mapper<Games, GamesDb> {
    override fun map(model: Games): GamesDb =
        with(model) {
            GamesDb(
                name = name,
                viewers = viewers,
                channels = channels,
                box = box
            )
        }
}