package com.attt.themovieapp.activiteis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.attt.themovieapp.R
import com.attt.themovieapp.adapters.MovieAdapter
import com.attt.themovieapp.data.models.MovieModel
import com.attt.themovieapp.data.models.MovieModelImpl
import com.attt.themovieapp.delegates.MovieViewHolderDelegate
import com.jakewharton.rxbinding4.widget.textChanges
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_movie_search.*
import java.util.concurrent.TimeUnit

class MovieSearchActivity : AppCompatActivity(), MovieViewHolderDelegate {

    //adapter
    private lateinit var mMovieAdapter: MovieAdapter

    //model
    private val mMovieModel:MovieModel=MovieModelImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_search)

        setupRecyclerView()
        setupListeners()
    }

    private fun setupListeners() {
        etSearch.textChanges()
            .debounce(500L, TimeUnit.MILLISECONDS)
            .flatMap {
                mMovieModel.searchedMovie(it.toString())
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                mMovieAdapter.setNewData(it)
            },{
                //show error messages
            })


    }

    private fun setupRecyclerView() {
        mMovieAdapter = MovieAdapter(this)
        rcSearch.adapter = mMovieAdapter
        rcSearch.layoutManager = GridLayoutManager(this, 2)
    }

    override fun onTabMovie(movieId: Int?) {

    }
}