package com.attt.themovieapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.attt.themovieapp.mvp.views.MovieDetailView

interface MovieDetailPresenter:IBasePresenter {
    fun initView(view:MovieDetailView)
    fun onUiReadyInMovieDetail(owner: LifecycleOwner,movieId:Int)
    fun onTapBack()
}