package com.attt.themovieapp.mvi.mvibase

interface MVIView<S:MVIStates> {
    fun render(state:S)
}