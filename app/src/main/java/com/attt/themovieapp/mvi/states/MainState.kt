package com.attt.themovieapp.mvi.states

import com.attt.themovieapp.data.VOS.ActorVO
import com.attt.themovieapp.data.VOS.GenreVO
import com.attt.themovieapp.data.VOS.MovieVO
import com.attt.themovieapp.mvi.mvibase.MVIStates

data class MainState(
    val isLoading: Boolean = false,
    val errorMessage:String,
    val nowPlayingMovies:List<MovieVO>,
    val popularMovies:List<MovieVO>,
    val topRatedMovies:List<MovieVO>,
    val genres:List<GenreVO>,
    val moviesByGenre:List<MovieVO>,
    val actors:List<ActorVO>,


):MVIStates{
    companion object{
        fun idle():MainState=MainState(
            isLoading = false,
            errorMessage = "",
            nowPlayingMovies = listOf(),
            popularMovies = listOf(),
            topRatedMovies = listOf(),
            genres = listOf(),
            moviesByGenre = listOf(),
            actors = listOf()

        )
    }
}


