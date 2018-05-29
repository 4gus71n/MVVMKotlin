package com.kimboo.androidjobsnewsletter.di.module

import android.content.Context
import com.kimboo.mvvmkotlin.db.AppDb
import com.kimboo.mvvmkotlin.db.UserDao
import com.kimboo.mvvmkotlin.retrofit.api.RandomUserApi
import com.kimboo.mvvmkotlin.retrofit.repositories.RandomUserRepository
import com.kimboo.mvvmkotlin.retrofit.repositories.RandomUserRepositoryImp
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideRecipesRepository(randomUserApi: RandomUserApi) : RandomUserRepository {
        return RandomUserRepositoryImp(randomUserApi)
    }

    @Provides
    fun providesUserDao(context: Context) : UserDao {
        return AppDb.create(context, false).users()
    }

}
