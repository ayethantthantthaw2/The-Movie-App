package com.attt.themovieapp.network.responses

import com.attt.themovieapp.data.VOS.DateVO
import com.attt.themovieapp.data.VOS.MovieVO
import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    @SerializedName("page")
    val page:Int?,

    @SerializedName("date")
    val date:DateVO?,

    @SerializedName("results")
    val results:List<MovieVO>?
)