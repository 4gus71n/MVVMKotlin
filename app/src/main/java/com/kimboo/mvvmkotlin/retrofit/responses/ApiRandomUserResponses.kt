package com.kimboo.mvvmkotlin.retrofit.responses

import com.google.gson.annotations.SerializedName

/**
 * Created by Agustin Tomas Larghi on 27/2/2018.
 * Email: agustin.tomas.larghi@gmail.com
 */
data class ApiRandomUserIdResponse(
        @SerializedName("value")
        val idValue: String,

        @SerializedName("name")
        val idName: String
)

data class ApiRandomUserLocationResponse(
        @SerializedName("street")
        val street: String,

        @SerializedName("city")
        val city: String,

        @SerializedName("state")
        val state: String,

        @SerializedName("postcode")
        val postcode: String
)

data class ApiRandomUserPictureResponse(
        @SerializedName("large")
        val large: String,

        @SerializedName("medium")
        val medium: String,

        @SerializedName("thumbnail")
        val thumbnail: String
)

data class ApiRandomResponse(
    @SerializedName("results")
    val results: List<ApiRandomUserResponse>,

    @SerializedName("info")
    val info: ApiRandomInfoResponse
)

data class ApiRandomInfoResponse(
    @SerializedName("results")
    val results: Int,

    @SerializedName("page")
    val page: Int
)

data class ApiRandomUserResponse(
        @SerializedName("email")
        val email: String,

        @SerializedName("id")
        val id: ApiRandomUserIdResponse,

        @SerializedName("location")
        val location: ApiRandomUserLocationResponse,

        @SerializedName("gender")
        val gender: String,

        @SerializedName("dob")
        val dob: String, //TODO Implement formatter to convert automatically to Calendar or Date

        @SerializedName("registered")
        val registered: String, //TODO Implement formatter to convert automatically to Calendar or Date

        @SerializedName("phone")
        val phone: String,

        @SerializedName("cell")
        val cell: String,

        @SerializedName("picture")
        val picture: ApiRandomUserPictureResponse,

        @SerializedName("nat")
        val nationality: String
)