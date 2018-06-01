package com.kimboo.mvvmkotlin.retrofit.api

import com.kimboo.mvvmkotlin.retrofit.responses.ApiRandomResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Agustin Tomas Larghi on 27/2/2018.
 * Email: agustin.tomas.larghi@gmail.com
 */
interface RandomUserApi {

    @GET("/api?seed=abc")
    fun getUserProfiles(@Query("page") page: Int, @Query("results") results: Int): Observable<Response<ApiRandomResponse>>

    @GET("/api")
    fun getUserProfile(@Query("email") email: String): Observable<Response<ApiRandomResponse>>

}