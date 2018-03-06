package com.kimboo.mvvmkotlin

import android.app.Application
import com.kimboo.androidjobsnewsletter.di.module.AppModule
import com.kimboo.mvvmkotlin.di.components.AppComponent
import com.kimboo.mvvmkotlin.di.components.DaggerAppComponent
import com.kimboo.mvvmkotlin.di.components.DaggerViewInjectorComponent
import com.kimboo.mvvmkotlin.di.components.ViewInjectorComponent

/**
 * Created by Agustin Tomas Larghi on 5/3/2018.
 * Email: agustin.tomas.larghi@gmail.com
 */
class MyApp: Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
        viewInjector = DaggerViewInjectorComponent.builder().appComponent(appComponent).build();
    }



    companion object {
        @JvmStatic lateinit var appComponent: AppComponent
        @JvmStatic lateinit var viewInjector: ViewInjectorComponent

        lateinit var instance: MyApp
            private set
    }

}