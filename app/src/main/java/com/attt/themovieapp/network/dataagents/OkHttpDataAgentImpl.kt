//package com.attt.themovieapp.network.dataagents
//
//import android.os.AsyncTask
//import com.attt.themovieapp.data.VOS.MovieVO
//import com.attt.themovieapp.network.responses.MovieListResponse
//import com.attt.themovieapp.utils.API_GET_NOW_PLAYING
//import com.attt.themovieapp.utils.BASE_URL
//import com.attt.themovieapp.utils.MOVIE_API_KEY
//import com.google.gson.Gson
//import okhttp3.OkHttpClient
//import okhttp3.Request
//import java.lang.Exception
//import java.util.concurrent.TimeUnit
//
//object OkHttpDataAgentImpl : MovieDataAgent {
//    private val mClient: OkHttpClient = OkHttpClient.Builder()
//        .connectTimeout(15, TimeUnit.SECONDS)
//        .readTimeout(15, TimeUnit.SECONDS)
//        .writeTimeout(15, TimeUnit.SECONDS)
//        .build()
//
//    override fun getNowPlayingMovies(onSuccess: (List<MovieVO>)->Unit,
//                                     onFailure: (String)->Unit) {
//        GetNowPlayingMovieOKHttpTask(mClient).execute()
//
//    }
//
//    class GetNowPlayingMovieOKHttpTask(private val okHttpClient: OkHttpClient) :
//        AsyncTask<Void, Void, MovieListResponse>() {
//        override fun doInBackground(vararg p0: Void?): MovieListResponse? {
//            val request = Request.Builder()
//                .url("$BASE_URL$API_GET_NOW_PLAYING?api_key=$MOVIE_API_KEY&language=en-US&page=1")
//                .build()
//            try {
//                val response = okHttpClient.newCall(request).execute()
//                if (response.isSuccessful) {
//                    response.body?.let {
//                        val responseString = it.string()
//                        val response = Gson().fromJson(
//                            responseString,
//                            MovieListResponse::class.java
//                        )
//                        return response
//                    }
//                }
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//            return null
//
//        }
//
//        override fun onPostExecute(result: MovieListResponse?) {
//            super.onPostExecute(result)
//        }
//
//
//    }
//}