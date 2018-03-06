package com.kimboo.mvvmkotlin.retrofit.mappers

import com.kimboo.mvvmkotlin.model.Recipe
import com.kimboo.mvvmkotlin.retrofit.responses.ApiRecipeResponse

/**
 * Created by Agustin Tomas Larghi on 6/3/2018.
 * Email: agustin.tomas.larghi@gmail.com
 */
class RecipesMapper {

    fun fromServerToModel(serverResponses: List<ApiRecipeResponse>): List<Recipe> {
        var result: MutableList<Recipe> = ArrayList()

        return result.toList()
    }

}