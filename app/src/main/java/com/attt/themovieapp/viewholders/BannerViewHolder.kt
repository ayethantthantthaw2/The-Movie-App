package com.attt.themovieapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.attt.themovieapp.data.VOS.MovieVO
import com.attt.themovieapp.delegates.BannerViewHolderDelegate
import com.attt.themovieapp.utils.IMAGE_BASE_URL
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.view_item_banner.view.*

class BannerViewHolder(itemView: View, private val mDelegate: BannerViewHolderDelegate) : RecyclerView.ViewHolder(itemView) {

        private var mMovie:MovieVO? = null

    init {
        itemView.setOnClickListener {
            mMovie?.let {movie->
                mDelegate.onTabMovieFromBanner(movie.id)
            }

        }
    }
    fun binData(movie: MovieVO) {
        mMovie=movie
        Glide.with(itemView.context)
            .load("${IMAGE_BASE_URL}${movie.posterPath}")
            .into(itemView.ivBannerImage)
        itemView.tvBannerMovieName.text=movie.title
    }
}