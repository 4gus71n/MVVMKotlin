package com.kimboo.mvvmkotlin.retrofit.api

import com.kimboo.mvvmkotlin.retrofit.responses.ApiRecipeResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Agustin Tomas Larghi on 27/2/2018.
 * Email: agustin.tomas.larghi@gmail.com
 */
interface RecipesApi {

    @GET("/recipes")
    fun getRecipe(): Observable<Response<List<ApiRecipeResponse>>>

    @GET("/recipes/{id}")
    fun getRecipe(@Path("id") recipeId: String): Observable<Response<ApiRecipeResponse>>

}