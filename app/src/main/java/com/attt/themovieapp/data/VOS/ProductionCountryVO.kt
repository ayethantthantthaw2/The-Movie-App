package com.attt.themovieapp.data.VOS

import com.google.gson.annotations.SerializedName

data class ProductionCountryVO(
    @SerializedName("iso_3166_1")
    val iso_3166_1:String?,

    @SerializedName("name")
    val name:String?,
)
