package com.attt.themovieapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.attt.themovieapp.data.VOS.ProductionCompanyVO
import com.attt.themovieapp.data.VOS.ProductionCountryVO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ProductionCountriesTypeConverter {
    @TypeConverter
    fun toString(productionCountriesList:List<ProductionCountryVO>?):String{
        return Gson().toJson(productionCountriesList)
    }

    @TypeConverter
    fun toProductionCountriesList(productionCountriesListJsonString:String):List<ProductionCountryVO>?{
        val productionCountriesListType=object : TypeToken<List<ProductionCountryVO>?>(){}.type
        return Gson().fromJson(productionCountriesListJsonString,productionCountriesListType)
    }
}