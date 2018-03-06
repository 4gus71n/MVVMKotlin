package com.kimboo.mvvmkotlin.di.components

import android.content.Context
import com.google.gson.Gson
import com.kimboo.androidjobsnewsletter.di.module.AppModule
import com.kimboo.androidjobsnewsletter.di.module.NetworkModule
import com.kimboo.androidjobsnewsletter.di.module.RepositoryModule
import com.kimboo.androidjobsnewsletter.di.module.RetrofitServiceModule
import com.kimboo.mvvmkotlin.MyApp
import com.kimboo.mvvmkotlin.di.modules.ViewModelModule
import com.kimboo.mvvmkotlin.retrofit.api.RecipesApi
import com.kimboo.mvvmkotlin.retrofit.repositories.RecipesRepository
import dagger.Component
import okhttp3.Cache

@Component(modules = arrayOf(RetrofitServiceModule::class, RepositoryModule::class, AppModule::class,
        NetworkModule::class, ViewModelModule::class))
interface AppComponent {

    val gson: Gson

    val cache: Cache

    val retrofit: retrofit2.Retrofit

    val applicationContext: Context

    val myApp: MyApp

    val recipesRepository: RecipesRepository

    val recipesApi: RecipesApi

}