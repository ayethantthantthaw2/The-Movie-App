package com.attt.themovieapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.attt.themovieapp.R
import com.attt.themovieapp.data.VOS.MovieVO
import com.attt.themovieapp.delegates.ShowcaseViewHolderDelegate
import com.attt.themovieapp.viewholders.ShowcaseViewHolder

class ShowcaseAdapter(private val mDelegate: ShowcaseViewHolderDelegate): RecyclerView.Adapter<ShowcaseViewHolder>() {

    private var mMovieList:List<MovieVO> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowcaseViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.view_holder_showcase,parent,false)
        return ShowcaseViewHolder(view,mDelegate)

    }

    override fun onBindViewHolder(holder: ShowcaseViewHolder, position: Int) {
        if (mMovieList.isNotEmpty()){
            holder.binData(mMovieList[position])
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