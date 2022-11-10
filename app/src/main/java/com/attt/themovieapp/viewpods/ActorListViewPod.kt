package com.attt.themovieapp.viewpods

import android.content.Context
import android.graphics.Paint
import android.icu.text.CaseMap
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.attt.themovieapp.adapters.ActorAdapter
import com.attt.themovieapp.data.VOS.ActorVO
import kotlinx.android.synthetic.main.view_pod_actor_list.view.*

class ActorListViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RelativeLayout(context, attrs) {
    lateinit var mActorAdapter:ActorAdapter
    override fun onFinishInflate() {
        setupActorRecyclerView()
        super.onFinishInflate()
    }
    fun setupActorViewPod(backgroundColorReference:Int,titleText:String,moreTitleText:String){
        setBackgroundColor(ContextCompat.getColor(context,backgroundColorReference))
        tvBestActor.text=titleText
        tvMoreActors.text=moreTitleText
        tvMoreActors.paintFlags= Paint.UNDERLINE_TEXT_FLAG
    }

    fun setData(actors:List<ActorVO>)
    {
        mActorAdapter.setNewData(actors)
    }



    private fun setupActorRecyclerView() {
        mActorAdapter= ActorAdapter()
        rvBestActor.adapter=mActorAdapter
        rvBestActor.layoutManager=LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)

    }
}