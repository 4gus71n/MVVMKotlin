package com.kimboo.mvvmkotlin.retrofit.responses

import com.google.gson.annotations.SerializedName

/**
 * Created by Agustin Tomas Larghi on 27/2/2018.
 * Email: agustin.tomas.larghi@gmail.com
 */
class ApiRecipeResponse {

    @SerializedName("name")
    lateinit var name: String

    @SerializedName("description")
    lateinit var description: String

    @SerializedName("ingredients")
    lateinit var ingredients: String

}