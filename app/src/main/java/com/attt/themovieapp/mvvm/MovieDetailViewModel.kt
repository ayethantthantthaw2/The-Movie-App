package com.attt.themovieapp.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.attt.themovieapp.data.VOS.ActorVO
import com.attt.themovieapp.data.VOS.MovieVO
import com.attt.themovieapp.data.models.MovieModelImpl

class MovieDetailViewModel:ViewModel() {
    //Model
    private val mMovieModel = MovieModelImpl



    //live data
    var movieDetailLiveData:LiveData<MovieVO?>?=null
    var castLiveData=MutableLiveData<List<ActorVO>>()
    var crewLiveData=MutableLiveData<List<ActorVO>>()
    var mErrorLiveData=MutableLiveData<String>()

    fun getInitialData(movieId:Int){
        movieDetailLiveData=mMovieModel.getMovieDetails(movieId = movieId.toString()){mErrorLiveData.postValue(it)}

        mMovieModel.getCreditsByMovie(movieId = movieId.toString(), onSuccess = {
            castLiveData.postValue(it.first?: listOf())
            crewLiveData.postValue(it.second?: listOf())
        },
        onFailure = {
            mErrorLiveData.postValue(it)
        })



    }
}