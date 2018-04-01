package com.kimboo.mvvmkotlin.di.modules

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.kimboo.mvvmkotlin.di.ViewModelKey
import com.kimboo.mvvmkotlin.ui.main.MainViewModel
import com.kimboo.mvvmkotlin.ui.recipedetail.RecipeDetailViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RecipeDetailViewModel::class)
    abstract fun bindRecipeDetailViewModel(recipeDetailViewModel: RecipeDetailViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: MyViewModelFactory): ViewModelProvider.Factory

}