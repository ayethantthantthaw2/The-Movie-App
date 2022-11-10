package com.attt.themovieapp.viewholders

import android.view.TouchDelegate
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.attt.themovieapp.data.VOS.MovieVO
import com.attt.themovieapp.delegates.MovieViewHolderDelegate
import com.attt.themovieapp.utils.IMAGE_BASE_URL
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.view_holder_movie.view.*
import kotlinx.android.synthetic.main.view_item_banner.view.*

class MovieViewHolder(itemView: View, mDelegate: MovieViewHolderDelegate) :
    RecyclerView.ViewHolder(itemView) {

    private var mMovie:MovieVO? = null

    init {
        itemView.setOnClickListener {
            mMovie?.let {
                mDelegate.onTabMovie(it.id)
            }
        }
    }

    fun bindData(movie: MovieVO) {
        mMovie=movie
        Glide.with(itemView.context)
            .load("$IMAGE_BASE_URL${movie.posterPath}")
            .into(itemView.ivMovieImage)
        itemView.tvMovieName.text = movie.title
        itemView.tvMovieRating.text = movie.vote_average?.toString()
        itemView.rbMovieRating.rating = movie.getMovieBasedOnFiveStars()


    }
}