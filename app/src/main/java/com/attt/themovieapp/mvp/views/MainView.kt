package com.attt.themovieapp.mvp.views

import com.attt.themovieapp.data.VOS.ActorVO
import com.attt.themovieapp.data.VOS.GenreVO
import com.attt.themovieapp.data.VOS.MovieVO

interface MainView:BaseView {
    fun showNowPlayingMovies(nowPlayingMovies:List<MovieVO>)
    fun showPopularMovies(popularMovies:List<MovieVO>)
    fun showTopRatedMovies(topRatedMovies:List<MovieVO>)
    fun showGenresList(genreList:List<GenreVO>)
    fun showMoviesByGenre(moviesByGenres:List<MovieVO>)
    fun showActors(actors:List<ActorVO>)
    fun navigateToMovieDetailsScreen(movieId:Int?)

}