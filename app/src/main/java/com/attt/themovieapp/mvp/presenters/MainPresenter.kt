package com.attt.themovieapp.mvp.presenters

import com.attt.themovieapp.delegates.BannerViewHolderDelegate
import com.attt.themovieapp.delegates.MovieViewHolderDelegate
import com.attt.themovieapp.delegates.ShowcaseViewHolderDelegate
import com.attt.themovieapp.mvp.views.MainView

interface MainPresenter:IBasePresenter,BannerViewHolderDelegate,ShowcaseViewHolderDelegate,MovieViewHolderDelegate {
    fun initView(view:MainView)
    fun onTapGenre(genrePosition:Int)
}