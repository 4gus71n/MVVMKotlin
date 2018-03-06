package com.kimboo.androidjobsnewsletter.di.module

import android.preference.PreferenceManager
import com.kimboo.mvvmkotlin.MyApp
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

@Module
class AppModule(private val mApplication: MyApp) {

    @Provides
    fun providesContext() = mApplication.applicationContext

    @Provides
    fun providesNewsletterApplication() = mApplication

    @Provides
    fun provideSharedPreferences() = PreferenceManager.getDefaultSharedPreferences(mApplication)

    @Provides
    fun provideNetworkScheduler() : Scheduler = Schedulers.io()

    @Provides
    fun provideDatabaseScheduler() : Scheduler = Schedulers.computation()

}
