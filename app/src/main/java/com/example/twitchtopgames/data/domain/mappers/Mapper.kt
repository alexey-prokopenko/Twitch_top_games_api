package com.example.twitchtopgames.data.domain.mappers

interface Mapper<E, K> {
    fun map(model: E): K
}