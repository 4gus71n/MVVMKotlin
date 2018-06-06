package com.kimboo.androidjobsnewsletter.di.module

import android.content.Context
import com.kimboo.mvvmkotlin.db.AppDb
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class RoomModule() {

    @Singleton
    @Provides
    fun providesAppDb(context: Context) = AppDb.create(context, false)

    @Singleton
    @Provides
    fun providesUserDao(appDb: AppDb) = appDb.users()

}
