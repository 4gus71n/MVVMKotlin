package com.kimboo.mvvmkotlin.di.modules

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.kimboo.mvvmkotlin.di.ViewModelKey
import com.kimboo.mvvmkotlin.ui.main.UserProfileListViewModel
import com.kimboo.mvvmkotlin.ui.userdetail.UserDetailViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(UserProfileListViewModel::class)
    abstract fun bindMainViewModel(userProfileListViewModel: UserProfileListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UserDetailViewModel::class)
    abstract fun bindRecipeDetailViewModel(userDetailViewModel: UserDetailViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: MyViewModelFactory): ViewModelProvider.Factory

}