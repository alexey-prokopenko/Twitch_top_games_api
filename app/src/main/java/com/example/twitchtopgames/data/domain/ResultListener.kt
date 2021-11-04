package com.example.twitchtopgames.data.domain

interface ResultListener<T> {

    fun onSuccess(data: T)

    fun onError(error: Throwable)
}