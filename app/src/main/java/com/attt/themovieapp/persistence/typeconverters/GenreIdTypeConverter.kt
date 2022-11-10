package com.attt.themovieapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.attt.themovieapp.data.VOS.GenreVO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GenreIdTypeConverter {
    @TypeConverter
    fun toString(genreIdList:List<Int>?):String{
        return Gson().toJson(genreIdList)
    }

    @TypeConverter
    fun toGenreIdList(genreIdListJsonString:String):List<Int>?{
        val genreIdListType=object : TypeToken<List<Int>?>(){}.type
        return Gson().fromJson(genreIdListJsonString,genreIdListType)
    }
}