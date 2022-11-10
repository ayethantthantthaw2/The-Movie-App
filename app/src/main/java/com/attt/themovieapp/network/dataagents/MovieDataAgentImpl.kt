//package com.attt.themovieapp.network.dataagents
//
//import android.os.AsyncTask
//import android.util.Log
//import com.attt.themovieapp.data.VOS.MovieVO
//import com.attt.themovieapp.network.responses.MovieListResponse
//import com.attt.themovieapp.utils.API_GET_NOW_PLAYING
//import com.attt.themovieapp.utils.BASE_URL
//import com.attt.themovieapp.utils.MOVIE_API_KEY
//import com.google.gson.Gson
//import java.io.BufferedReader
//import java.io.IOException
//import java.io.InputStreamReader
//import java.io.StringReader
//import java.lang.Exception
//import java.lang.StringBuilder
//import java.net.HttpURLConnection
//import java.net.URL
//
//object MovieDataAgentImpl:MovieDataAgent {
//    override fun getNowPlayingMovies(onSuccess: (List<MovieVO>)->Unit,
//                                     onFailure: (String)->Unit) {
//        GetNowPlayingTask().execute()
//
//    }
//    class GetNowPlayingTask():AsyncTask<Void,Void,MovieListResponse?>(){
//        override fun doInBackground(vararg p0: Void?): MovieListResponse? {
//            val url:URL
//            var reader:BufferedReader?=null
//            val stringBuilder:StringBuilder
//            try {
//                url=URL("""$BASE_URL$API_GET_NOW_PLAYING?api_key=$MOVIE_API_KEY&language=en-US&page=1""")//1
//                val connection=url.openConnection() as HttpURLConnection//2
//
//                //set HTTP Method
//                connection.requestMethod="GET"//3
//
//                //give it 15 seconds to respond
//                connection.readTimeout=15*1000//4 ms
//
//                connection.doInput=true//5
//                connection.doOutput=false
//
//                connection.connect()//7
//
//                //read the output from the server
//                reader= BufferedReader(InputStreamReader(connection.inputStream))
//                //8
//                stringBuilder= StringBuilder()
//
//                for (line in reader.readLines()){
//                    stringBuilder.append(line+"\n")
//                }
//                val responseString=stringBuilder.toString()
//                Log.d("Now Playing Movie",responseString)
//
//                val movieListResponse=Gson().fromJson(
//                    responseString,MovieListResponse::class.java
//                )
//                return movieListResponse
//            }catch (e:Exception){
//                e.printStackTrace()
//                Log.e("NewsError",e.message?:"")
//            }finally {
//                if (reader!=null){
//                    try {
//                        reader.close()
//                    }catch (ioe:IOException){
//                        ioe.printStackTrace()
//
//                    }
//                }
//            }
//        return null
//        }
//
//        override fun onPostExecute(result: MovieListResponse?) {
//            super.onPostExecute(result)
//        }
//    }
//
//}