package com.attt.themovieapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.attt.themovieapp.data.VOS.CollectionVO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CollectionTypeConverter {
    @TypeConverter
    fun toString(collection: CollectionVO?): String {
        return Gson().toJson(collection)

    }

    @TypeConverter
    fun toCollectionVO(commentListGsonStr: String): CollectionVO? {
        val collectionVOType=object: TypeToken<CollectionVO?>(){}.type
        return Gson().fromJson(commentListGsonStr,collectionVOType)
    }
}