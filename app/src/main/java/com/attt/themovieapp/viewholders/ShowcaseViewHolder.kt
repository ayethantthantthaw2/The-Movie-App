package com.attt.themovieapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.attt.themovieapp.data.VOS.MovieVO
import com.attt.themovieapp.delegates.ShowcaseViewHolderDelegate
import com.attt.themovieapp.utils.IMAGE_BASE_URL
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.view_holder_showcase.view.*
import kotlinx.android.synthetic.main.view_item_banner.view.*

class ShowcaseViewHolder(itemView: View,mDelegate: ShowcaseViewHolderDelegate) : RecyclerView.ViewHolder(itemView) {

    private var mMovie:MovieVO? = null

    init {
        itemView.setOnClickListener {
            mMovie?.let {
                mDelegate.onTabMovieFromShowcase(it.id)
            }
        }
    }
    fun binData(movie: MovieVO) {
        mMovie=movie
        Glide.with(itemView.context)
            .load("$IMAGE_BASE_URL${movie.posterPath}")
            .into(itemView.ivShowcase)
        itemView.tvShowcasesMovieName.text=movie.title
        itemView.tvShowcasesMovieDate.text=movie.releaseDate
    }
}