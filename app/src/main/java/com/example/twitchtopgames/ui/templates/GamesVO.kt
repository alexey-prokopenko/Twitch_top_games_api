package com.example.twitchtopgames.ui.templates

class GamesVO(
    val channels: String,
    val viewers: String,
    val name: String,
    val box: String
) : VisualObject {
    override val id: String
        get() = name
}