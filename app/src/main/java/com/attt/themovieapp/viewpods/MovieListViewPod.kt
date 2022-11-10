package com.attt.themovieapp.viewpods

import android.content.AbstractThreadedSyncAdapter
import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.attt.themovieapp.adapters.MovieAdapter
import com.attt.themovieapp.data.VOS.MovieVO
import com.attt.themovieapp.delegates.MovieViewHolderDelegate
import kotlinx.android.synthetic.main.view_pod_movie_list.view.*

class MovieListViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {
    lateinit var mDelegate:MovieViewHolderDelegate
    lateinit var mMovieAdapter: MovieAdapter
    override fun onFinishInflate() {
        //setUpMovieRecyclerView()
        super.onFinishInflate()
    }
    fun setData(movieList:List<MovieVO>){
        mMovieAdapter.setNewData(movieList)
    }
    fun setupMovieViewPod(delegate: MovieViewHolderDelegate){
        setupDelegate(delegate)
        setUpMovieRecyclerView()
    }
    private fun setupDelegate(delegate: MovieViewHolderDelegate){
        this.mDelegate=delegate
    }

    private fun setUpMovieRecyclerView() {
        mMovieAdapter= MovieAdapter(mDelegate)
        rvMovieList.adapter=mMovieAdapter
        rvMovieList.layoutManager=LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
    }
}