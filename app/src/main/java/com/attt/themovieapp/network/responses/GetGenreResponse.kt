package com.attt.themovieapp.network.responses

import com.attt.themovieapp.data.VOS.GenreVO
import com.google.gson.annotations.SerializedName

data class GetGenreResponse(
    @SerializedName("genres")
    val genres:List<GenreVO>?
)