package com.example.twitchtopgames.ui.mappers


import com.example.twitchtopgames.data.domain.GamesDb
import com.example.twitchtopgames.data.domain.mappers.Mapper
import com.example.twitchtopgames.ui.templates.GamesVO

class GamesVoMapper : Mapper<GamesDb, GamesVO> {
    override fun map(model: GamesDb): GamesVO =
        with(model) {
            GamesVO(
                channels = channels,
                viewers = viewers,
                name = name,
                box = box
            )
        }
}