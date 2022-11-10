package com.attt.themovieapp.mvi.intents

import com.attt.themovieapp.mvi.mvibase.MVIIntent

sealed class MainIntent:MVIIntent{
    class LoadMoviesByGenreIntent(val genrePosition:Int):MainIntent()
    object LoadAllHomePagesData:MainIntent()
}
