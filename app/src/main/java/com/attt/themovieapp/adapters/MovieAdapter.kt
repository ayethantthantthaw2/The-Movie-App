package com.attt.themovieapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.attt.themovieapp.R
import com.attt.themovieapp.data.VOS.MovieVO
import com.attt.themovieapp.delegates.MovieViewHolderDelegate
import com.attt.themovieapp.viewholders.MovieViewHolder

class MovieAdapter(private val mDelegate:MovieViewHolderDelegate) : RecyclerView.Adapter<MovieViewHolder>() {

    private var mMovieList:List<MovieVO> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie, parent, false)
        return MovieViewHolder(view,mDelegate)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        if (mMovieList.isNotEmpty()){
            holder.bindData(mMovieList[position])
        }
    }

    override fun getItemCount(): Int {
        return mMovieList.count()
    }
    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(movieList:List<MovieVO>){
        mMovieList=movieList
        notifyDataSetChanged()

    }
}