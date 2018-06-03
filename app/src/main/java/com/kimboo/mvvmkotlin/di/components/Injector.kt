package com.kimboo.mvvmkotlin.di.components

import com.kimboo.androidjobsnewsletter.di.module.*
import com.kimboo.mvvmkotlin.di.modules.ViewModelModule
import com.kimboo.mvvmkotlin.ui.edituserdetail.EditUserDetailProfileFragment
import com.kimboo.mvvmkotlin.ui.userdetail.UserDetailProfileFragment
import com.kimboo.mvvmkotlin.ui.userlist.UserProfileListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class), (RoomModule::class), (NetworkModule::class),
    (RetrofitServiceModule::class), (RepositoryModule::class), (ViewModelModule::class)])
interface Injector {

    fun inject(userProfileListFragment: UserProfileListFragment)

    fun inject(editUserDetailProfileFragment: EditUserDetailProfileFragment)

    fun inject(userDetailProfileFragment: UserDetailProfileFragment)

}