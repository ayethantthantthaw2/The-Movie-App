package com.attt.themovieapp.network.responses

import com.attt.themovieapp.data.VOS.ActorVO
import com.attt.themovieapp.data.VOS.MovieVO
import com.google.gson.annotations.SerializedName

data class GetActorsResponse(
    @SerializedName("results")
    val results:List<ActorVO>?,
)
