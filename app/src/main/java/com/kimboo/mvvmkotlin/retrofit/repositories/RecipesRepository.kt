package com.kimboo.mvvmkotlin.retrofit.repositories

import com.kimboo.mvvmkotlin.extensions.DataSource
import com.kimboo.mvvmkotlin.model.Recipe
import io.reactivex.Observable

/**
 * Created by Agustin Tomas Larghi on 27/2/2018.
 * Email: agustin.tomas.larghi@gmail.com
 */
interface RecipesRepository {

    fun getRecipeList(): Observable<DataSource<List<Recipe>>>

    fun getRecipe(recipeId: String): Observable<DataSource<Recipe>>

}