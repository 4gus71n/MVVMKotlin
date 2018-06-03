package com.kimboo.mvvmkotlin.di.modules

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.kimboo.mvvmkotlin.di.ViewModelKey
import com.kimboo.mvvmkotlin.ui.edituserdetail.EditUserDetailViewModel
import com.kimboo.mvvmkotlin.ui.userdetail.UserDetailViewModel
import com.kimboo.mvvmkotlin.ui.userlist.UserProfileListViewModel
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
    abstract fun bindUserDetailViewModel(userDetailViewModel: UserDetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EditUserDetailViewModel::class)
    abstract fun bindEditUserDetailViewModel(editUserDetailViewModel: EditUserDetailViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: MyViewModelFactory): ViewModelProvider.Factory

}