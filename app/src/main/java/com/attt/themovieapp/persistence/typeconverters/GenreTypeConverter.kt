package com.attt.themovieapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.attt.themovieapp.data.VOS.GenreVO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GenreTypeConverter {
    @TypeConverter
    fun toString(genreList:List<GenreVO>?):String{
        return Gson().toJson(genreList)
    }

    @TypeConverter
    fun toGenreList(genreListJsonString:String):List<GenreVO>?{
        val genreListType=object :TypeToken<List<GenreVO>?>(){}.type
        return Gson().fromJson(genreListJsonString,genreListType)
    }
}