package com.attt.themovieapp.mvp.views

import com.attt.themovieapp.data.VOS.ActorVO
import com.attt.themovieapp.data.VOS.MovieVO
import com.attt.themovieapp.delegates.BannerViewHolderDelegate
import com.attt.themovieapp.delegates.MovieViewHolderDelegate
import com.attt.themovieapp.delegates.ShowcaseViewHolderDelegate
import com.attt.themovieapp.mvp.presenters.IBasePresenter

interface MovieDetailView:BaseView {
    fun showMovieDetails(movie:MovieVO)
    fun showCreditsByMovies(cast:List<ActorVO>,crew:List<ActorVO>)
    fun navigateBack()

}