package com.attt.themovieapp.interactors

import androidx.lifecycle.LiveData
import com.attt.themovieapp.data.VOS.ActorVO
import com.attt.themovieapp.data.VOS.GenreVO
import com.attt.themovieapp.data.VOS.MovieVO
import io.reactivex.rxjava3.core.Observable

interface MovieInteractor {
    fun getNowPlayingMovies(
        onFailure: (String)->Unit
    ): LiveData<List<MovieVO>>?
    fun getPopularMovies(
        onFailure: (String)->Unit
    ): LiveData<List<MovieVO>>?
    fun getTopRatedMovie(
        onFailure: (String)->Unit
    ): LiveData<List<MovieVO>>?
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
        onFailure: (String)->Unit
    ): LiveData<MovieVO?>?

    fun getCreditsByMovie(
        movieId:String,
        onSuccess: (Pair<List<ActorVO>,List<ActorVO>>)->Unit,
        onFailure: (String)->Unit
    )
    fun searchedMovie(
        query:String
    ): Observable<List<MovieVO>>

}