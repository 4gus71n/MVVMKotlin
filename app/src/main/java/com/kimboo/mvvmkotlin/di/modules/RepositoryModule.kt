package com.kimboo.androidjobsnewsletter.di.module

import com.kimboo.mvvmkotlin.db.UserDao
import com.kimboo.mvvmkotlin.retrofit.api.RandomUserApi
import com.kimboo.mvvmkotlin.retrofit.repositories.RandomUserRepository
import com.kimboo.mvvmkotlin.retrofit.repositories.RandomUserRepositoryImp
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideRecipesRepository(randomUserApi: RandomUserApi, userDao: UserDao) : RandomUserRepository {
        return RandomUserRepositoryImp(randomUserApi, userDao)
    }

}
