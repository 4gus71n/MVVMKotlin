package com.kimboo.androidjobsnewsletter.di.module

import com.kimboo.mvvmkotlin.retrofit.api.RecipesApi
import com.kimboo.mvvmkotlin.retrofit.repositories.RecipesRepository
import com.kimboo.mvvmkotlin.retrofit.repositories.RecipesRepositoryImp
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideRecipesRepository(recipesApi: RecipesApi) : RecipesRepository {
        return RecipesRepositoryImp(recipesApi)
    }

}
