package com.attt.themovieapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.attt.themovieapp.data.models.MovieModel
import com.attt.themovieapp.data.models.MovieModelImpl
import com.attt.themovieapp.interactors.MovieInteractor
import com.attt.themovieapp.interactors.MovieInteractorImpl
import com.attt.themovieapp.mvp.views.MainView
import com.attt.themovieapp.mvp.views.MovieDetailView

class MovieDetailPresenterImpl : ViewModel(), MovieDetailPresenter {
    //View
    private var mView: MovieDetailView? = null

    //Interactor
    private val mMovieIntreactor: MovieInteractor = MovieInteractorImpl

    //model
    private val mMovieModel: MovieModel = MovieModelImpl
    override fun initView(view: MovieDetailView) {
        mView = view
    }

    override fun onUiReadyInMovieDetail(owner: LifecycleOwner, movieId: Int) {
        //movieDetails
        mMovieIntreactor.getMovieDetails(movieId.toString()) {
            mView?.showErrors(it)
        }?.observe(owner) {
            it.let {
                it?.let { it1 -> mView?.showMovieDetails(it1) }
            }
        }
        //credits
        mMovieIntreactor.getCreditsByMovie(movieId.toString(), onSuccess = {
            mView?.showCreditsByMovies(it.first, it.second)
        }, onFailure = {
            mView?.showErrors(it)
        })


    }

    override fun onTapBack() {
        mView?.navigateBack()
    }

    override fun onUiReady(owner: LifecycleOwner) {

    }
}