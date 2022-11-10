package com.attt.themovieapp.activiteis

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.attt.themovieapp.R
import com.attt.themovieapp.data.VOS.ActorVO
import com.attt.themovieapp.data.VOS.GenreVO
import com.attt.themovieapp.data.VOS.MovieVO
import com.attt.themovieapp.data.models.MovieModel
import com.attt.themovieapp.data.models.MovieModelImpl
import com.attt.themovieapp.mvp.presenters.MainPresenter
import com.attt.themovieapp.mvp.presenters.MovieDetailPresenter
import com.attt.themovieapp.mvp.presenters.MovieDetailPresenterImpl
import com.attt.themovieapp.mvp.views.MovieDetailView
import com.attt.themovieapp.mvvm.MovieDetailViewModel
import com.attt.themovieapp.utils.IMAGE_BASE_URL
import com.attt.themovieapp.viewpods.ActorListViewPod
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.view_holder_movie.*

class MovieDetailActivity : AppCompatActivity(),MovieDetailView {

    companion object {
        private const val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"
        fun newIntent(context: Context, movieId: Int?): Intent {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(EXTRA_MOVIE_ID, movieId)
            return intent

        }
    }

    //View Pods
    lateinit var actorViewPod: ActorListViewPod
    lateinit var creatorViewPod: ActorListViewPod

    //models
    //private val mMovieModel: MovieModel = MovieModelImpl

    //viewModel
    private  var  mViewModel: MovieDetailViewModel? = null

    //Presenter
    private lateinit var mPresenter: MovieDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)


        setupPresenter()
        setupViewPods()
        setupListener()
        //requestData(movieId = )
        observeLiveData()


        val movieId = intent?.getIntExtra(EXTRA_MOVIE_ID, 0)
        // Toast.makeText(this, "$movieId", Toast.LENGTH_LONG).show()

        movieId?.let {
           // requestData(movieId)
            mPresenter.onUiReadyInMovieDetail(this,movieId)
            setupViewModel(it)
        }
    }

    private fun observeLiveData() {
        mViewModel?.movieDetailLiveData?.observe(this){
           it.let { movie ->
               if (movie != null) {
                   bindData(movie)
               }
           }
        }
        mViewModel?.castLiveData?.observe(this,actorViewPod::setData)
        mViewModel?.crewLiveData?.observe(this,creatorViewPod::setData)
    }

    private fun setupViewModel(movieId: Int) {
        mViewModel=ViewModelProvider(this)[MovieDetailViewModel::class.java]
        mViewModel?.getInitialData(movieId)

    }

    private fun setupPresenter() {
        mPresenter=ViewModelProvider(this)[MovieDetailPresenterImpl::class.java]
        mPresenter.initView(this)
    }

//    private fun requestData(movieId: Int) {
//        mMovieModel.getMovieDetails(
//            movieId = movieId.toString(),
//            onFailure = {
//                //show error message
//            }
//
//        )?.observe(this) {
//            it?.let { it1 -> bindData(it1) }
//        }
//        //get credits bu movie
//        mMovieModel.getCreditsByMovie(
//            movieId = movieId.toString(),
//            onSuccess = {
//                actorViewPod.setData(it.first)
//                creatorViewPod.setData(it.second)
//            }, onFailure = {
//                //show error message
//            }
//
//        )
//
//    }

    private fun bindData(movie: MovieVO) {
        Glide.with(this)
            .load("$IMAGE_BASE_URL${movie.posterPath}")
            .into(ivMovieDetail)
        tvMovieDetailName.text = movie.title ?: ""
        tvReleaseYear.text = movie.releaseDate?.substring(0, 4)
        tvRating.text = movie.vote_average.toString() ?: ""
        movie.voteCount?.let {
            tvNumberOfVotes.text = "$it VOTES"
        }

        rbRating.rating = movie.getMovieBasedOnFiveStars()
        bindGenre(movie, movie.genres ?: listOf())

        tvOverview.text = movie.overview ?: ""
        tvOriginalTitle.text = movie.title ?: ""
        tvType.text = movie.getGenresCommasSeparatedString()
        tvProduction.text = movie.getProductionCountriesAsCommasSeparatedString()
        tvPremiere.text = movie.releaseDate ?: ""
        tvDescription.text = movie.overview ?: ""


    }

    private fun bindGenre(movie: MovieVO, genre: List<GenreVO>) {
        movie.genres?.count()?.let {
            tvFirstGenre.text = genre.firstOrNull()?.name ?: ""
            tvSecondGenre.text = genre.getOrNull(1)?.name ?: ""
            tvThirdGenre.text = genre.getOrNull(2)?.name ?: ""

            if (it < 3) {
                tvThirdGenre.visibility = View.GONE
            } else if (it < 2) {
                tvSecondGenre.visibility = View.GONE
            }

        }


    }

    private fun setupListener() {
        btnBack.setOnClickListener {
            super.onBackPressed()
        }
    }


    private fun setupViewPods() {
        actorViewPod = vpActors as ActorListViewPod
        actorViewPod.setupActorViewPod(
            backgroundColorReference = R.color.colorPrimary,
            titleText = getString(R.string.lbl_actors),
            moreTitleText = ""
        )

        creatorViewPod = vpCreators as ActorListViewPod
        creatorViewPod.setupActorViewPod(
            backgroundColorReference = R.color.colorPrimary,
            titleText = getString(R.string.lbl_creators),
            moreTitleText = getString(R.string.lbl_more_creators)

        )


    }

    override fun showMovieDetails(movie: MovieVO) {
        bindData(movie)
    }

    override fun showCreditsByMovies(cast: List<ActorVO>, crew: List<ActorVO>) {
        actorViewPod.setData(cast)
        creatorViewPod.setData(crew)
    }

    override fun navigateBack() {
        finish()
    }

    override fun showErrors(errorsString: String) {
        Log.d("Error",errorsString)
    }
}