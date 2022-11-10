package com.attt.themovieapp.network.dataagents

import com.attt.themovieapp.data.VOS.ActorVO
import com.attt.themovieapp.data.VOS.GenreVO
import com.attt.themovieapp.data.VOS.MovieVO
import com.attt.themovieapp.data.VOS.ProductionCompanyVO

interface MovieDataAgent {
    fun getNowPlayingMovies(
        onSuccess: (List<MovieVO>)->Unit,
        onFailure: (String)->Unit
    )

    fun getPopularMovies(
        onSuccess: (List<MovieVO>)->Unit,
        onFailure: (String)->Unit
    )

    fun getTopRatedMovies(
        onSuccess: (List<MovieVO>)->Unit,
        onFailure: (String)->Unit
    )
    fun getGenres(
        onSuccess: (List<GenreVO>)->Unit,
        onFailure: (String)->Unit
    )
    fun getMoviesByGenres(
        genreId:String,
        onSuccess: (List<MovieVO>)->Unit,
        onFailure: (String)->Unit
    )

    fun getActors(
        onSuccess: (List<ActorVO>)->Unit,
        onFailure: (String)->Unit
    )

    fun getMovieDetails(
        movieId:String,
        onSuccess: (MovieVO)->Unit,
        onFailure: (String)->Unit
    )

    fun getCreditsByMovie(
        movieId:String,
        onSuccess: (Pair<List<ActorVO>,List<ActorVO>>)->Unit,
        onFailure: (String)->Unit
    )


}