package com.attt.themovieapp.network.responses

import com.attt.themovieapp.data.VOS.ActorVO
import com.google.gson.annotations.SerializedName

data class GetCreditsByMovieResponse(
    @SerializedName("cast")
    val cast:List<ActorVO>,

    @SerializedName("crew")
    val crew:List<ActorVO>,
)
