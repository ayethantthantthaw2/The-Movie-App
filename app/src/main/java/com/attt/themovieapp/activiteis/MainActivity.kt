package com.attt.themovieapp.activiteis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.attt.themovieapp.R
import com.attt.themovieapp.adapters.BannerAdapter
import com.attt.themovieapp.adapters.ShowcaseAdapter
import com.attt.themovieapp.data.VOS.ActorVO
import com.attt.themovieapp.data.VOS.GenreVO
import com.attt.themovieapp.data.VOS.MovieVO
import com.attt.themovieapp.data.models.MovieModel
import com.attt.themovieapp.data.models.MovieModelImpl
import com.attt.themovieapp.delegates.BannerViewHolderDelegate
import com.attt.themovieapp.delegates.MovieViewHolderDelegate
import com.attt.themovieapp.delegates.ShowcaseViewHolderDelegate
import com.attt.themovieapp.mvi.intents.MainIntent
import com.attt.themovieapp.mvi.mvibase.MVIView
import com.attt.themovieapp.mvi.states.MainState
import com.attt.themovieapp.mvp.presenters.MainPresenter
import com.attt.themovieapp.mvp.presenters.MainPresenterImpl
import com.attt.themovieapp.mvp.views.MainView
import com.attt.themovieapp.mvvm.MainViewModel
import com.attt.themovieapp.routers.navigateToMovieDetailsActivity
import com.attt.themovieapp.routers.navigateToMovieSearchActivity
import com.attt.themovieapp.viewholders.BannerViewHolder
//import com.attt.themovieapp.network.dataagents.MovieDataAgentImpl
//import com.attt.themovieapp.network.dataagents.OkHttpDataAgentImpl
import com.attt.themovieapp.viewpods.ActorListViewPod
import com.attt.themovieapp.viewpods.MovieListViewPod
import com.google.android.material.tabs.TabLayout
import dummy.dummyGenreList
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MainActivity : AppCompatActivity(), MainView, BannerViewHolderDelegate,
    ShowcaseViewHolderDelegate, MovieViewHolderDelegate, MVIView<MainState> {

    lateinit var mBannerAdapter: BannerAdapter
    lateinit var mShowcaseAdapter: ShowcaseAdapter

    lateinit var mBestPopularMovieListViewPod: MovieListViewPod
    lateinit var mMovieByGenreViewPod: MovieListViewPod
    lateinit var mActorListViewPod: ActorListViewPod

//    //models
//    private val mMovieModel: MovieModel = MovieModelImpl
//
//    //data
//    private var mGenres: List<GenreVO>? = null

    //viewModel
    // private lateinit var mViewModel: MainViewModel
    // private lateinit var mViewModel: com.attt.themovieapp.mvi.viewmodels.MainViewModel

    //Presenter
    private lateinit var mPresenter: MainPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // setupViewModel()


        setupPresenter()

        setupToolbar()
        setupViewPod()
        setupBannerAdapter()
        //setupGenreTabLayout()
        setupShowcaseRecyclerView()

        setupListeners()

        setInitialIntent()
        observeState()
        //MovieDataAgentImpl.getNowPlayingMovies()
        //OkHttpDataAgentImpl.getNowPlayingMovies()
        // RetrofitDataAgentImpl.getNowPlayingMovies()

        mPresenter.onUiReady(this)

        //observe liveData
        // observeLiveData()


    }

    private fun observeState() {
        //  mViewModel.state.observe(this, this::render)
    }


    private fun setInitialIntent() {
//        mViewModel.processIntent(MainIntent.LoadAllHomePagesData, this)
    }

//    private fun observeLiveData() {
//        mViewModel.nowPlayingMovieLiveData?.observe(this, mBannerAdapter::setNewData)
//        mViewModel.popularMoviesLiveData?.observe(this, mBestPopularMovieListViewPod::setData)
//        mViewModel.topRatedMoviesLiveData?.observe(this, mShowcaseAdapter::setNewData)
//        mViewModel.genreLiveData?.observe(this, this::setupGenreTabLayout)
//        mViewModel.actorsliveData?.observe(this, mActorListViewPod::setData)
//        mViewModel.moviesByGenreLiveData?.observe(this, mMovieByGenreViewPod::setData)
//    }

    //    private fun setupViewModel() {
//        mViewModel = ViewModelProvider(this)[MainViewModel::class.java]
//       mViewModel.getInitialData()
//    }
    private fun setupViewModel() {
        //   mViewModel =
        ViewModelProvider(this)[com.attt.themovieapp.mvi.viewmodels.MainViewModel::class.java]

    }

    private fun setupPresenter() {
        mPresenter = ViewModelProvider(this)[MainPresenterImpl::class.java]
        mPresenter.initView(this)
    }

    private fun requestData() {
//        //Get Now Playing
//        mMovieModel.getNowPlayingMovies(
//            onFailure = {
//                ///show error message
//            }
//        )?.observe(this) {
//            mBannerAdapter.setNewData(it)
//        }
//
//        //get Popular
//        mMovieModel.getPopularMovies(
//            onFailure = {
//                ///show error message
//            }
//        )?.observe(this){
//            mBestPopularMovieListViewPod.setData(it)
//        }
//
//        //Get Top_Rated
//        mMovieModel.getTopRatedMovie(
//
//            onFailure = {
//                ///show error message
//            }
//        )?.observe(this){
//            mShowcaseAdapter.setNewData(it)
//        }
//        //get movies by genres
//        mMovieModel.getGenres(
//            onSuccess = {
//                mGenres = it
//                setupGenreTabLayout(it)
//
//                //Get Movies By Genes For First Genre
//                it.firstOrNull()?.id?.let { genreId -> getMoviesByGenres(genreId) }
//
//            },
//            onFailure = {
//                //show error message
//            }
//        )
//        //get actors
//        mMovieModel.getActors(
//            onSuccess = {
//                mActorListViewPod.setData(it)
//            },
//            onFailure = {
//                //show error Message
//            }
//        )
//
//
//    }
//
//    private fun getMoviesByGenres(genreId: Int) {
//        mMovieModel.getMoviesByGenres(
//            genreId = genreId.toString(),
//            onSuccess = {
//                mMovieByGenreViewPod.setData(it)
//            },
//            onFailure = {
//                //show error message
//            }
//        )
    }


    private fun setupViewPod() {
        mBestPopularMovieListViewPod = vpBestPopularMovieList as MovieListViewPod
        mBestPopularMovieListViewPod.setupMovieViewPod(mPresenter)

        mMovieByGenreViewPod = vpMovieByGenre as MovieListViewPod
        mMovieByGenreViewPod.setupMovieViewPod(
            mPresenter
        )

        mActorListViewPod = vpActorList as ActorListViewPod
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_discover, menu)
        return true
    }

    private fun setupToolbar() {
        setSupportActionBar(toolBar)
        //App Bar Leading Icon
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
    }

    private fun setupBannerAdapter() {
        mBannerAdapter = BannerAdapter(mPresenter)
        viewPagerBanner.adapter = mBannerAdapter

        //dots indicator
        dotsIndicatorBanner.attachTo(viewPagerBanner)
    }

    private fun setupGenreTabLayout(genreList: List<GenreVO>) {
        genreList.forEach {
            tabLayoutGenre.newTab().apply {
                text = it.name
                tabLayoutGenre.addTab(this)
            }
        }


    }

    private fun setupShowcaseRecyclerView() {
        mShowcaseAdapter = ShowcaseAdapter(mPresenter)
        rvShowcases.adapter = mShowcaseAdapter
        rvShowcases.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setupListeners() {
        //Genre TabLayout
        tabLayoutGenre.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                mPresenter?.onTapGenre(tab?.position ?: 0)
                //getMoviesByGenres(it)
                //  mViewModel.getMoviesGenre(tab?.position ?: 0)
//                mViewModel.processIntent(
//                    MainIntent.LoadMoviesByGenreIntent(tab?.position ?: 0), this@MainActivity
//                )
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }


        })
    }

    override fun onTabMovieFromBanner(movieId: Int?) {

        startActivity(MovieDetailActivity.newIntent(this, movieId = movieId))
    }

    override fun onTabMovieFromShowcase(movieId: Int?) {
        startActivity(MovieDetailActivity.newIntent(this, movieId = movieId))
    }

    override fun onTabMovie(movieId: Int?) {
        startActivity(MovieDetailActivity.newIntent(this, movieId = movieId))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.itemSearch -> //startActivity(Intent(this, MovieSearchActivity::class.java))
                navigateToMovieSearchActivity()
            else -> super.onOptionsItemSelected(item)
        }

        return super.onOptionsItemSelected(item)
    }

    override fun showNowPlayingMovies(nowPlayingMovies: List<MovieVO>) {
        mBannerAdapter.setNewData(nowPlayingMovies)
    }

    override fun showPopularMovies(popularMovies: List<MovieVO>) {
        mBestPopularMovieListViewPod.setData(popularMovies)
    }

    override fun showTopRatedMovies(topRatedMovies: List<MovieVO>) {
        mShowcaseAdapter.setNewData(topRatedMovies)

    }

    override fun showGenresList(genreList: List<GenreVO>) {
        setupGenreTabLayout(genreList)
    }

    override fun showMoviesByGenre(moviesByGenres: List<MovieVO>) {
        mMovieByGenreViewPod.setData(moviesByGenres)
    }

    override fun showActors(actors: List<ActorVO>) {
        mActorListViewPod.setData(actors)
    }

    override fun navigateToMovieDetailsScreen(movieId: Int?) {
        // startActivity(MovieDetailActivity.newIntent(this, movieId = movieId))
        navigateToMovieDetailsActivity(movieId ?: 0)
    }

    override fun showErrors(errorsString: String) {
        Log.d("Error", errorsString)
    }

    override fun render(state: MainState) {
        if (state.errorMessage.isNotEmpty()) {
            showErrors(state.errorMessage)
        }
        mBannerAdapter.setNewData(state.nowPlayingMovies)
        mBestPopularMovieListViewPod.setData(state.popularMovies)
        mShowcaseAdapter.setNewData(state.topRatedMovies)
        setupGenreTabLayout(state.genres)
        mMovieByGenreViewPod.setData(state.moviesByGenre)
        mActorListViewPod.setData(state.actors)
    }


}