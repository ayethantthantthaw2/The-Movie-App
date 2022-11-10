package com.attt.themovieapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.attt.themovieapp.data.VOS.ActorVO
import com.attt.themovieapp.utils.IMAGE_BASE_URL
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.view_holder_actor.view.*
import kotlinx.android.synthetic.main.view_item_banner.view.*
import kotlinx.android.synthetic.main.view_pod_actor_list.view.*

class ActorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bindData(actors: ActorVO) {
        Glide.with(itemView.context)
            .load("$IMAGE_BASE_URL${actors.profile_path}")
            .into(itemView.ivBestActor)
        itemView.tvActorName.text=actors.name

    }
}