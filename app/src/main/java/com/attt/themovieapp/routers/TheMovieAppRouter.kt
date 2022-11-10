package com.attt.themovieapp.routers

import android.app.Activity
import android.content.Intent
import com.attt.themovieapp.activiteis.MovieDetailActivity
import com.attt.themovieapp.activiteis.MovieSearchActivity

fun Activity.navigateToMovieDetailsActivity(movieId:Int){
    startActivity(MovieDetailActivity.newIntent(this,movieId=movieId))
}
fun Activity.navigateToMovieSearchActivity(){
    val intent=Intent(this,MovieSearchActivity::class.java)
    startActivity(intent)
}