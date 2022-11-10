package com.attt.themovieapp.mvi.viewmodels

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.attt.themovieapp.mvi.intents.MainIntent
import com.attt.themovieapp.mvi.mvibase.MVIViewModel
import com.attt.themovieapp.mvi.processors.MainProcessor
import com.attt.themovieapp.mvi.states.MainState

class MainViewModel(override var state: MutableLiveData<MainState> = MutableLiveData(MainState.idle())) :
    MVIViewModel<MainState, MainIntent>, ViewModel() {
    private val mProcessor = MainProcessor

    override fun processIntent(intent: MainIntent, lifecycleOwner: LifecycleOwner) {
        when(intent){
            //Load Home Page Data
            MainIntent.LoadAllHomePagesData->{
                state.value?.let {
                    mProcessor.loadAllHomePagesData(
                        previousState = it
                    ).observe(lifecycleOwner){
                        newState->
                        state.postValue(newState)
                        if (newState.moviesByGenre.isEmpty()){
                            processIntent(MainIntent.LoadMoviesByGenreIntent(0),lifecycleOwner)
                        }
                    }
                }
            }
            //Load Movies By genres
            is MainIntent.LoadMoviesByGenreIntent->
                state.value?.let {
                    val genreId=it.genres.getOrNull(intent.genrePosition)?.id ?:0
                    mProcessor.loadMoviesByGenres(
                        genreId = genreId,
                        previousState = it
                    ).observe(lifecycleOwner,state::postValue)
                }
        }

    }
}