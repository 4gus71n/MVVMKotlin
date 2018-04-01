package com.kimboo.mvvmkotlin.retrofit.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Agustin Tomas Larghi on 27/2/2018.
 * Email: agustin.tomas.larghi@gmail.com
 */
class ApiRecipeResponse {

    @SerializedName("chef_id")
    @Expose
    lateinit var chefId: String

    @SerializedName("chef_name")
    @Expose
    lateinit var chefName: String

    @SerializedName("name")
    @Expose
    lateinit var name: String

    @SerializedName("description")
    @Expose
    lateinit var description: String

    @SerializedName("ingredients")
    @Expose
    lateinit var ingredients: List<ApiIngredientResponse>

}