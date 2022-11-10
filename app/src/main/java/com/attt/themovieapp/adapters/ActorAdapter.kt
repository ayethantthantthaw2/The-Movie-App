package com.attt.themovieapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.attt.themovieapp.R
import com.attt.themovieapp.data.VOS.ActorVO
import com.attt.themovieapp.viewholders.ActorViewHolder

class ActorAdapter: RecyclerView.Adapter<ActorViewHolder>() {

    private var mActors:List<ActorVO> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.view_holder_actor,parent,false)
        return ActorViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        if (mActors.isNotEmpty()){
            holder.bindData(mActors[position])
        }
    }

    override fun getItemCount(): Int {
        return mActors.count()
    }

    @SuppressLint("notifyDataSetChanged")
    fun setNewData(actors: List<ActorVO>) {
        mActors=actors
        notifyDataSetChanged()
    }
}