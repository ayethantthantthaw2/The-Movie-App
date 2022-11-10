package com.attt.themovieapp.data.VOS

import com.google.gson.annotations.SerializedName

data class SpokenLanguageVO(
    @SerializedName("english_name")
    val english_name:String?,

    @SerializedName("iso_639_1")
    val iso_639_1:String?,

    @SerializedName("name")
    val name:String?,
)