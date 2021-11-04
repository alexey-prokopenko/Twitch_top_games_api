package com.example.twitchtopgames.ui

import com.example.twitchtopgames.ui.templates.VisualObject

interface GamesView {
    fun showLoader(isShow: Boolean)

    fun showMessage(message: String)

    fun updateGames(games: List<VisualObject>)
}