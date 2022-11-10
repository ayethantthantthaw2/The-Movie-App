package com.attt.themovieapp.mvvm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.attt.themovieapp.data.VOS.ActorVO
import com.attt.themovieapp.data.VOS.GenreVO
import com.attt.themovieapp.data.VOS.MovieVO
import com.attt.themovieapp.data.models.MovieModelImpl

class MainViewModel : ViewModel() {
    //Model
    private val mMovieModel = MovieModelImpl

    //LiveData
    var nowPlayingMovieLiveData: LiveData<List<MovieVO>>? = null
    var popularMoviesLiveData: LiveData<List<MovieVO>>? = null
    var topRatedMoviesLiveData: LiveData<List<MovieVO>>? = null
    var genreLiveData = MutableLiveData<List<GenreVO>>()
    var moviesByGenreLiveData = MutableLiveData<List<MovieVO>>()
    var actorsliveData = MutableLiveData<List<ActorVO>>()
    var mErrorLiveDAta = MutableLiveData<String>()

    fun getInitialData() {
        nowPlayingMovieLiveData = mMovieModel.getNowPlayingMovies { mErrorLiveDAta.postValue(it) }
        popularMoviesLiveData = mMovieModel.getPopularMovies { mErrorLiveDAta.postValue(it) }
        popularMoviesLiveData = mMovieModel.getTopRatedMovie { mErrorLiveDAta.postValue(it) }

        mMovieModel.getGenres(
            onSuccess = {
                genreLiveData.postValue(it)
                getMoviesGenre(0)
            },
            onFailure = {
                mErrorLiveDAta.postValue(it)
            }


        )
        mMovieModel.getActors(
            onSuccess = {
                actorsliveData.postValue(it)

            },
            onFailure = {
                mErrorLiveDAta.postValue(it)
            }


        )
    }

     fun getMoviesGenre(genrePosition: Int) {
        Log.d("Genres", "${genreLiveData.value}")
        genreLiveData.value?.getOrNull(genrePosition)?.id.let {
            mMovieModel.getMoviesByGenres(it.toString(), { moviesByGenre ->
                moviesByGenreLiveData.postValue(moviesByGenre)
            }, onFailure = { errorMessage ->
                mErrorLiveDAta.postValue(errorMessage)
            })

        }

    }

}