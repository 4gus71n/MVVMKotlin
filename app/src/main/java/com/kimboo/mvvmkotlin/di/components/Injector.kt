package com.kimboo.mvvmkotlin.di.components

import com.kimboo.androidjobsnewsletter.di.module.*
import com.kimboo.mvvmkotlin.di.modules.ViewModelModule
import com.kimboo.mvvmkotlin.ui.main.UserProfileListFragment
import com.kimboo.mvvmkotlin.ui.userdetail.UserDetailProfileFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class), (RoomModule::class), (NetworkModule::class),
    (RetrofitServiceModule::class), (RepositoryModule::class), (ViewModelModule::class)])
interface Injector {

    fun inject(fragment: UserProfileListFragment)

    fun inject(profileFragment: UserDetailProfileFragment)

}