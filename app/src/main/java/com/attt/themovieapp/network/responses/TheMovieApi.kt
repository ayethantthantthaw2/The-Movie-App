package com.attt.themovieapp.network.responses

import com.attt.themovieapp.data.VOS.MovieVO
import com.attt.themovieapp.utils.*
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieApi {
    @GET(API_GET_NOW_PLAYING)
    fun getNowPlayingMovie(
        @Query(PARAM_API_KEY) apikey:String= MOVIE_API_KEY,
        @Query(PARAM_PAGE) page:Int=1,
    ):Observable<MovieListResponse>

    @GET(API_GET_POPULAR)
    fun getPopularMovies(
        @Query(PARAM_API_KEY) apikey:String= MOVIE_API_KEY,
        @Query(PARAM_PAGE) page:Int=1,
    ):Observable<MovieListResponse>

    @GET(API_GET_TOP_RATED)
    fun getTopRatedMovies(
        @Query(PARAM_API_KEY) apikey:String= MOVIE_API_KEY,
        @Query(PARAM_PAGE) page:Int=1,
    ):Observable<MovieListResponse>

    @GET(API_GET_GENRE)
    fun getGenres(
        @Query(PARAM_API_KEY) apikey:String= MOVIE_API_KEY,
    ):Observable<GetGenreResponse>

    @GET(API_GET_GENRE_BY_ID)
    fun getMoviesByGenre(
        @Query(PARAM_GENRE_ID) genreId:String,
        @Query(PARAM_API_KEY) apikey:String= MOVIE_API_KEY,

        ):Observable<MovieListResponse>

    @GET(API_GET_ACTORS)
    fun getActors(
        @Query(PARAM_API_KEY) apikey:String= MOVIE_API_KEY,
        @Query(PARAM_PAGE) page:Int=1,
    ):Observable<GetActorsResponse>

    @GET("$API_GET_MOVIE_DETAIL/{movie_id}")
    fun getMovieDetail(
        @Path("movie_id") movieId:String?,
        @Query(PARAM_API_KEY) apikey:String= MOVIE_API_KEY,
        @Query(PARAM_PAGE) page:Int=1,
    ):Observable<MovieVO>

    @GET("$API_GET_CREDITS_BY_MOVIE/{movie_id}/credits")
    fun getCreditsByMovie(
        @Path("movie_id") movieId:String?,
        @Query(PARAM_API_KEY) apikey:String= MOVIE_API_KEY,
        @Query(PARAM_PAGE) page:Int=1,
    ):Observable<GetCreditsByMovieResponse>

    @GET(API_SEARCH)
    fun searchMovie(
        @Query(PARAM_API_KEY) apikey:String= MOVIE_API_KEY,
        @Query(PARAM_QUERY) query:String
    ):Observable<MovieListResponse>


}