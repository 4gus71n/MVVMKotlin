package com.kimboo.androidjobsnewsletter.di.module

import com.kimboo.mvvmkotlin.retrofit.api.RandomUserApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
/**
 * Created by Agustin Tomas Larghi on 9/12/2017.
 * Email: agustin.tomas.larghi@gmail.com
 */
@Module
class RetrofitServiceModule {

    @Provides
    fun provideRecipesApi(retrofit: Retrofit): RandomUserApi {
        return retrofit.create(RandomUserApi::class.java)
    }

}

