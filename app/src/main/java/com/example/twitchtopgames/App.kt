package com.example.twitchtopgames

import android.app.Application
import android.content.Context

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    companion object {

        private lateinit var appInstance: App

        fun applicationContext(): Context {
            return appInstance.applicationContext
        }
    }
}