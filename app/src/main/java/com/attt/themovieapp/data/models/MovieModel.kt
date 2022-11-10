package com.attt.themovieapp.data.models

import io.reactivex.rxjava3.core.Observable
import androidx.lifecycle.LiveData
import com.attt.themovieapp.data.VOS.ActorVO
import com.attt.themovieapp.data.VOS.GenreVO
import com.attt.themovieapp.data.VOS.MovieVO
import com.attt.themovieapp.data.VOS.ProductionCompanyVO

interface MovieModel {
    fun getNowPlayingMovies(
        onFailure: (String)->Unit
    ):LiveData<List<MovieVO>>?
    fun getPopularMovies(
        onFailure: (String)->Unit
    ):LiveData<List<MovieVO>>?
    fun getTopRatedMovie(
        onFailure: (String)->Unit
    ):LiveData<List<MovieVO>>?
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
    ):LiveData<MovieVO?>?

    fun getCreditsByMovie(
        movieId:String,
        onSuccess: (Pair<List<ActorVO>,List<ActorVO>>)->Unit,
        onFailure: (String)->Unit
    )
    fun searchedMovie(
        query:String
    ):Observable<List<MovieVO>>

    //Reactive streams only
    fun getNowPlayingMoviesObservable():Observable<List<MovieVO>>?
    fun getPopularMoviesObservable():Observable<List<MovieVO>>?
    fun getTopRatedMoviesObservable():Observable<List<MovieVO>>?
    fun getGenresObservable():Observable<List<GenreVO>>?
    fun getActorsObservable():Observable<List<ActorVO>>?
    fun getMoviesByGenresObservable(genreId:String):Observable<List<MovieVO>>?
    fun getMoviesByIdObservable(movieId:Int):Observable<MovieVO>?
    fun getCreditByMoviesObservable(movieId:Int):Observable<Pair<List<ActorVO>,List<ActorVO>>>?


}