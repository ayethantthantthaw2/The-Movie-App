package com.attt.themovieapp

import android.app.Application
import com.attt.themovieapp.data.models.MovieModelImpl

class MovieApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        MovieModelImpl.initDatabase(applicationContext)
    }
}