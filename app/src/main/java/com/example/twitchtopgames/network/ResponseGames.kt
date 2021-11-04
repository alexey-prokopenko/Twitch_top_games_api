package com.example.twitchtopgames.network

import com.squareup.moshi.Json

data class ResponseGames(

    @Json(name = "top")
    val top: List<TopItem?>? = null,

    @Json(name = "_total")
    val total: Int? = null
)

data class Box(

    @Json(name = "small")
    val small: String? = null,

    @Json(name = "template")
    val template: String? = null,

    @Json(name = "large")
    val large: String? = null,

    @Json(name = "medium")
    val medium: String? = null
)

data class Game(

    @Json(name = "giantbomb_id")
    val giantbombId: Int? = null,

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "logo")
    val logo: Logo? = null,

    @Json(name = "box")
    val box: Box? = null,

    @Json(name = "_id")
    val id: Int? = null,

    @Json(name = "locale")
    val locale: String? = null,

    @Json(name = "localized_name")
    val localizedName: String? = null
)

data class TopItem(

    @Json(name = "game")
    val game: Game? = null,

    @Json(name = "viewers")
    val viewers: Int? = null,

    @Json(name = "channels")
    val channels: Int? = null
)

data class Logo(

    @Json(name = "small")
    val small: String? = null,

    @Json(name = "template")
    val template: String? = null,

    @Json(name = "large")
    val large: String? = null,

    @Json(name = "medium")
    val medium: String? = null
)
