package com.attt.themovieapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.attt.themovieapp.data.VOS.GenreVO
import com.attt.themovieapp.data.VOS.ProductionCompanyVO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ProductionCompaniesTypeConverter {
    @TypeConverter
    fun toString(productionCompaniesList:List<ProductionCompanyVO>?):String{
        return Gson().toJson(productionCompaniesList)
    }

    @TypeConverter
    fun toProductionCompaniesList(productionCompaniesListJsonString:String):List<ProductionCompanyVO>?{
        val productionCompaniesListType=object : TypeToken<List<ProductionCompanyVO>?>(){}.type
        return Gson().fromJson(productionCompaniesListJsonString,productionCompaniesListType)
    }
}