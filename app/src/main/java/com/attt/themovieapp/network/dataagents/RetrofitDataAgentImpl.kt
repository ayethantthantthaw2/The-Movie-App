//package com.attt.themovieapp.network.dataagents
//
//import com.attt.themovieapp.data.VOS.ActorVO
//import com.attt.themovieapp.data.VOS.GenreVO
//import com.attt.themovieapp.data.VOS.MovieVO
//import com.attt.themovieapp.network.responses.*
//import com.attt.themovieapp.utils.BASE_URL
//import okhttp3.OkHttpClient
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//import java.util.concurrent.TimeUnit
//
//object RetrofitDataAgentImpl : MovieDataAgent {
//
//    private var mTheMovieApi: TheMovieApi? = null
//
//    init {
//        val mOkHttpClient = OkHttpClient.Builder()
//            .connectTimeout(15, TimeUnit.SECONDS)
//            .readTimeout(15, TimeUnit.SECONDS)
//            .writeTimeout(15, TimeUnit.SECONDS)
//            .build()
//
//        val retrofit = Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .client(mOkHttpClient)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//        mTheMovieApi = retrofit.create(TheMovieApi::class.java)
//
//    }
//
//    override fun getNowPlayingMovies(
//        onSuccess: (List<MovieVO>) -> Unit,
//        onFailure: (String) -> Unit
//    ) {
//        mTheMovieApi?.getNowPlayingMovie()
//            ?.enqueue(object : Callback<MovieListResponse> {
//                override fun onResponse(
//                    call: Call<MovieListResponse>,
//                    response: Response<MovieListResponse>
//                ) {
//                    if (response.isSuccessful) {
//                        val movieList = response.body()?.results ?: listOf()
//                        onSuccess(movieList)
//
//                    } else {
//                        onFailure(response.message())
//
//                    }
//
//                }
//
//                override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
//
//                }
//            })
//
//
//    }
//
//    override fun getPopularMovies(
//        onSuccess: (List<MovieVO>) -> Unit,
//        onFailure: (String) -> Unit
//    ) {
//        mTheMovieApi?.getPopularMovies()
//            ?.enqueue(object : Callback<MovieListResponse> {
//                override fun onResponse(
//                    call: Call<MovieListResponse>,
//                    response: Response<MovieListResponse>
//                ) {
//                    if (response.isSuccessful) {
//                        val movieList = response.body()?.results ?: listOf()
//                        onSuccess(movieList)
//
//                    } else {
//                        onFailure(response.message())
//
//                    }
//
//                }
//
//                override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
//
//                }
//            })
//
//
//    }
//
//    override fun getTopRatedMovies(
//        onSuccess: (List<MovieVO>) -> Unit,
//        onFailure: (String) -> Unit
//    ) {
//        mTheMovieApi?.getTopRatedMovies()
//            ?.enqueue(object : Callback<MovieListResponse> {
//                override fun onResponse(
//                    call: Call<MovieListResponse>,
//                    response: Response<MovieListResponse>
//                ) {
//                    if (response.isSuccessful) {
//                        val movieList = response.body()?.results ?: listOf()
//                        onSuccess(movieList)
//
//                    } else {
//                        onFailure(response.message())
//
//                    }
//
//                }
//
//                override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
//
//                }
//            })
//
//
//    }
//
//    override fun getGenres(onSuccess: (List<GenreVO>) -> Unit, onFailure: (String) -> Unit) {
//
//        mTheMovieApi?.getGenres()
//            ?.enqueue(object : Callback<GetGenreResponse> {
//                override fun onResponse(
//                    call: Call<GetGenreResponse>,
//                    response: Response<GetGenreResponse>
//                ) {
//                    if (response.isSuccessful) {
//                        val genreList = response.body()?.genres ?: listOf()
//                        onSuccess(genreList)
//
//                    } else {
//                        onFailure(response.message())
//
//                    }
//
//                }
//
//                override fun onFailure(call: Call<GetGenreResponse>, t: Throwable) {
//
//                }
//            })
//    }
//
//    override fun getMoviesByGenres(
//        genreId: String,
//        onSuccess: (List<MovieVO>) -> Unit,
//        onFailure: (String) -> Unit
//    ) {
//        mTheMovieApi?.getMoviesByGenre(genreId = genreId)
//            ?.enqueue(object : Callback<MovieListResponse> {
//                override fun onResponse(
//                    call: Call<MovieListResponse>,
//                    response: Response<MovieListResponse>
//                ) {
//                    if (response.isSuccessful) {
//                        val movieList = response.body()?.results ?: listOf()
//                        onSuccess(movieList)
//
//                    } else {
//                        onFailure(response.message())
//
//                    }
//
//                }
//
//                override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
//
//                }
//            })
//    }
//
//    override fun getActors(onSuccess: (List<ActorVO>) -> Unit, onFailure: (String) -> Unit) {
//        mTheMovieApi?.getActors()
//            ?.enqueue(object : Callback<GetActorsResponse> {
//                override fun onResponse(
//                    call: Call<GetActorsResponse>,
//                    response: Response<GetActorsResponse>
//                ) {
//                    if (response.isSuccessful) {
//                        val actorsList = response.body()?.results ?: listOf()
//                        onSuccess(actorsList)
//
//                    } else {
//                        onFailure(response.message())
//
//                    }
//
//                }
//
//                override fun onFailure(call: Call<GetActorsResponse>, t: Throwable) {
//
//                }
//            })
//    }
//
//    override fun getMovieDetails(
//        movieId: String,
//        onSuccess: (MovieVO) -> Unit,
//        onFailure: (String) -> Unit
//    ) {
//        mTheMovieApi?.getMovieDetail(movieId = movieId)
//            ?.enqueue(object : Callback<MovieVO> {
//                override fun onResponse(
//                    call: Call<MovieVO>,
//                    response: Response<MovieVO>
//                ) {
//                    if (response.isSuccessful) {
//                        response.body()?.let {
//                            onSuccess(it)
//                        }
//
//                    } else {
//                        onFailure(response.message())
//
//                    }
//
//                }
//
//                override fun onFailure(call: Call<MovieVO>, t: Throwable) {
//
//                }
//            })
//    }
//
//    override fun getCreditsByMovie(
//        movieId: String,
//        onSuccess: (Pair<List<ActorVO>, List<ActorVO>>) -> Unit,
//        onFailure: (String) -> Unit
//    ) {
//        mTheMovieApi?.getCreditsByMovie(movieId = movieId)
//            ?.enqueue(object : Callback<GetCreditsByMovieResponse> {
//                override fun onResponse(
//                    call: Call<GetCreditsByMovieResponse>,
//                    response: Response<GetCreditsByMovieResponse>
//                ) {
//                    if (response.isSuccessful) {
//                        response.body()?.let {
//                            onSuccess(Pair(it.cast ?: listOf(), it.crew ?: listOf()))
//                        }
//
//                    } else {
//                        onFailure(response.message())
//
//                    }
//
//                }
//
//                override fun onFailure(call: Call<GetCreditsByMovieResponse>, t: Throwable) {
//
//                }
//            })
//    }
//
//
//}