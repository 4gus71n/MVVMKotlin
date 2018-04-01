package com.kimboo.mvvmkotlin.retrofit.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Agustin Tomas Larghi on 7/3/2018.
 * Email: agustin.tomas.larghi@gmail.com
 */
class ApiIngredientResponse {

    @SerializedName("name")
    @Expose
    lateinit var name: String

    @SerializedName("units")
    @Expose
    lateinit var units: String

    @SerializedName("amount")
    @Expose
    var amount: Int = -1
}