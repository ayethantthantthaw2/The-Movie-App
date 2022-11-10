package com.attt.themovieapp.data.models

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import com.attt.themovieapp.data.VOS.*
import com.attt.themovieapp.network.responses.MovieListResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.core.Observable


object MovieModelImpl : BaseModel(), MovieModel {
    //data agent
    // private val mMovieDataAgent: MovieDataAgent = RetrofitDataAgentImpl

    //database
//    private var mMovieDatabase: MovieDatabase? = null
//
//    fun initDatabase(context: Context) {
//        mMovieDatabase = MovieDatabase.getDBInstance(context)
//    }

    @SuppressLint("CheckResult")
    override fun getNowPlayingMovies(
        onFailure: (String) -> Unit
    ): LiveData<List<MovieVO>>? {
        //database
        // onSuccess(mMovieDatabase?.movieDao()?.getMoviesByType(type = NOW_PLAYING) ?: listOf())
        //network
        mMovieAPi.getNowPlayingMovie(page = 1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.results?.forEach { movie -> movie.type = NOW_PLAYING }
                mMovieDatabase?.movieDao()?.insertMovies(it.results ?: listOf())


            }, { onFailure(it.localizedMessage ?: "") })
        return mMovieDatabase?.movieDao()?.getMoviesByType(type = NOW_PLAYING)
    }

    override fun getPopularMovies(onFailure: (String) -> Unit): LiveData<List<MovieVO>>? {
        //database
        //onSuccess(mMovieDatabase?.movieDao()?.getMoviesByType(type = POPULAR) ?: listOf())

        mMovieAPi.getPopularMovies(page = 1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.results?.forEach { movie -> movie.type = POPULAR }
                mMovieDatabase?.movieDao()?.insertMovies(it.results ?: listOf())

                //to view layer
                // onSuccess(it.results ?: listOf())
            }, { onFailure(it.localizedMessage ?: "") })
        return mMovieDatabase?.movieDao()?.getMoviesByType(type = POPULAR)
    }

    override fun getTopRatedMovie(onFailure: (String) -> Unit): LiveData<List<MovieVO>>? {
        //database
        //onSuccess(mMovieDatabase?.movieDao()?.getMoviesByType(type = TOP_RATED) ?: listOf())
        mMovieAPi.getTopRatedMovies(page = 1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.results?.forEach { movie -> movie.type = TOP_RATED }
                mMovieDatabase?.movieDao()?.insertMovies(it.results ?: listOf())

                //to view layer
                //onSuccess(it.results ?: listOf())
            }, { onFailure(it.localizedMessage ?: "") })
        return mMovieDatabase?.movieDao()?.getMoviesByType(type = TOP_RATED)
    }

    override fun getGenres(onSuccess: (List<GenreVO>) -> Unit, onFailure: (String) -> Unit) {
        mMovieAPi.getGenres()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onSuccess(it.genres ?: listOf())
            }, { onFailure(it.localizedMessage ?: "") })
    }

    override fun getMoviesByGenres(
        genreId: String,
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieAPi.getMoviesByGenre(genreId = genreId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onSuccess(it.results ?: listOf())
            }, { onFailure(it.localizedMessage ?: "") })
    }

    override fun getActors(onSuccess: (List<ActorVO>) -> Unit, onFailure: (String) -> Unit) {
        mMovieAPi.getActors(page = 1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onSuccess(it.results ?: listOf())
            }, { onFailure(it.localizedMessage ?: "") })

    }

    override fun getMovieDetails(
        movieId: String,
        onFailure: (String) -> Unit
    ): LiveData<MovieVO?>? {
        //database
//        val movieFromDatabase = mMovieDatabase?.movieDao()?.getMovieById(movieId = movieId.toInt())
//        movieFromDatabase?.let {
//            //to view layer
//           // onSuccess(it)
//        }
        //network
        mMovieAPi.getMovieDetail(movieId = movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                //insert data from network to persistence
                val movieFromDatabaseToSync =
                    mMovieDatabase?.movieDao()?.getMovieByIdOneTime(movieId = movieId.toInt())
                it.type = movieFromDatabaseToSync?.type
                mMovieDatabase?.movieDao()?.insertSingleMovie(it)

                //to view layer
                //onSuccess(it)
            }, { onFailure(it.localizedMessage ?: "") })
        return mMovieDatabase?.movieDao()?.getMovieById(movieId = movieId.toInt())
    }

    override fun getCreditsByMovie(
        movieId: String,
        onSuccess: (Pair<List<ActorVO>, List<ActorVO>>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieAPi.getCreditsByMovie(movieId = movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onSuccess(Pair(it.cast ?: listOf(), it.crew ?: listOf()))
            }, { onFailure(it.localizedMessage ?: "") })
    }

    override fun searchedMovie(query: String): Observable<List<MovieVO>> {
        return mMovieAPi
            .searchMovie(query = query)
            .map { it.results ?: listOf() }
            .onErrorResumeNext { Observable.just(listOf()) }
            .subscribeOn(Schedulers.io())
    }

    override fun getNowPlayingMoviesObservable(): Observable<List<MovieVO>>? {
        mMovieAPi.getNowPlayingMovie(page = 1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.results?.forEach { movie -> movie.type = NOW_PLAYING }
                mMovieDatabase?.movieDao()?.insertMovies(it.results ?: listOf())


            }, { (it.localizedMessage ?: "") })
        return mMovieDatabase?.movieDao()
            ?.getMoviesByTypeFlowable(type = NOW_PLAYING)
            ?.toObservable()

    }

    override fun getPopularMoviesObservable(): Observable<List<MovieVO>>? {
        mMovieAPi.getPopularMovies(page = 1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.results?.forEach { movie -> movie.type = POPULAR }
                mMovieDatabase?.movieDao()?.insertMovies(it.results ?: listOf())


            }, { (it.localizedMessage ?: "") })
        return mMovieDatabase?.movieDao()
            ?.getMoviesByTypeFlowable(type = POPULAR)
            ?.toObservable()
    }

    override fun getTopRatedMoviesObservable(): Observable<List<MovieVO>>? {
        mMovieAPi.getTopRatedMovies(page = 1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.results?.forEach { movie -> movie.type = TOP_RATED }
                mMovieDatabase?.movieDao()?.insertMovies(it.results ?: listOf())


            }, { (it.localizedMessage ?: "") })
        return mMovieDatabase?.movieDao()
            ?.getMoviesByTypeFlowable(type = TOP_RATED)
            ?.toObservable()
    }

    override fun getGenresObservable(): Observable<List<GenreVO>>? {
        return mMovieAPi.getGenres().map { it.genres ?: listOf() }.subscribeOn(Schedulers.io())
    }

    override fun getActorsObservable(): Observable<List<ActorVO>>? {
        return mMovieAPi.getActors().map { it.results ?: listOf() }.subscribeOn(Schedulers.io())
    }

    override fun getMoviesByGenresObservable(genreId: String): Observable<List<MovieVO>>? {
        return mMovieAPi.getMoviesByGenre(genreId = genreId).map { it.results ?: listOf() }.subscribeOn(Schedulers.io())
    }

    override fun getMoviesByIdObservable(movieId: Int): Observable<MovieVO>? {
        mMovieAPi.getMovieDetail(movieId = movieId.toString())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                //insert data from network to persistence
                val movieFromDatabaseToSync =
                    mMovieDatabase?.movieDao()?.getMovieByIdOneTime(movieId = movieId.toInt())
                it.type = movieFromDatabaseToSync?.type
                mMovieDatabase?.movieDao()?.insertSingleMovie(it)

                //to view layer
                //onSuccess(it)
            }, { (it.localizedMessage ?: "") })
        return mMovieDatabase?.movieDao()?.getMovieByIdFlowable(movieId = movieId)
            ?.toObservable()
    }

    override fun getCreditByMoviesObservable(movieId: Int): Observable<Pair<List<ActorVO>, List<ActorVO>>>? {
        return mMovieAPi.getCreditsByMovie(movieId = movieId.toString())
            .map { Pair(it.cast?: listOf(),it.crew) }
            .subscribeOn(Schedulers.io())
    }


}
