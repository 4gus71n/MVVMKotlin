package com.kimboo.mvvmkotlin

import android.app.Application
import com.facebook.stetho.Stetho
import com.kimboo.androidjobsnewsletter.di.module.AppModule
import com.kimboo.mvvmkotlin.db.AppDb
import com.kimboo.mvvmkotlin.di.components.DaggerInjector
import com.kimboo.mvvmkotlin.di.components.Injector

/**
 * Created by Agustin Tomas Larghi on 5/3/2018.
 * Email: agustin.tomas.larghi@gmail.com
 */
class MyApp: Application() {

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this);
        instance = this

        AppDb.create(this, false)

        viewInjector = DaggerInjector
                .builder()
                .appModule(AppModule(this))
                .build()
    }



    companion object {
        @JvmStatic lateinit var viewInjector: Injector

        lateinit var instance: MyApp
            private set

        const val TAG = "MVVMExample"
    }

}