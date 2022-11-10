package com.attt.themovieapp.mvp.presenters

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.attt.themovieapp.data.VOS.GenreVO
import com.attt.themovieapp.data.models.MovieModel
import com.attt.themovieapp.data.models.MovieModelImpl
import com.attt.themovieapp.interactors.MovieInteractor
import com.attt.themovieapp.interactors.MovieInteractorImpl
import com.attt.themovieapp.mvp.views.MainView

class MainPresenterImpl: ViewModel(),MainPresenter {

    //View
    private var mView:MainView?=null

    //model
    private val mMovieModel:MovieModel=MovieModelImpl

    //Interactor
    private val mMovieIntreactor:MovieInteractor = MovieInteractorImpl

    //states
    private var mGenre:List<GenreVO> = listOf()

    override fun initView(view: MainView) {
        mView=view

    }

    override fun onUiReady(owner: LifecycleOwner) {
        //NowPlaying
        mMovieIntreactor.getNowPlayingMovies {
            mView?.showErrors(it)
        }?.observe(owner){
            mView?.showNowPlayingMovies(it)}

        //Popular
        mMovieIntreactor.getPopularMovies {
            mView?.showErrors(it)
        }?.observe(owner){
            mView?.showPopularMovies(it)}

        //TopRated
        mMovieIntreactor.getTopRatedMovie {
            mView?.showErrors(it)
        }?.observe(owner){
            mView?.showTopRatedMovies(it)}

        //Genre and get Movies for first genres
        mMovieIntreactor?.getGenres(
            onSuccess = {
                mGenre=it
                mView?.showGenresList(it)
                it.firstOrNull()?.id?.let {
                    firstGenreId->
                    onTapGenre(firstGenreId)
                }
            },
            onFailure = {
                mView?.showErrors(it)
            }

        )
    //actors
        mMovieIntreactor?.getActors(
            onSuccess = {
                mView?.showActors(it)
            },
            onFailure = {
                mView?.showErrors(it)
            }
        )


    }

    override fun onTabMovieFromBanner(movieId: Int?) {
        mView?.navigateToMovieDetailsScreen(movieId)

    }

    override fun onTabMovieFromShowcase(movieId: Int?) {
        mView?.navigateToMovieDetailsScreen(movieId)
    }

    override fun onTabMovie(movieId: Int?) {
        mView?.navigateToMovieDetailsScreen(movieId)

    }

    override fun onTapGenre(genrePosition: Int) {
        mGenre?.getOrNull(genrePosition)?.id?.let {
            genreId-> mMovieModel.getMoviesByGenres(genreId = genreId.toString(), onSuccess = {
                mView?.showMoviesByGenre(it)
        }, onFailure = {
            mView?.showErrors(it)
        })

        }


    }
}