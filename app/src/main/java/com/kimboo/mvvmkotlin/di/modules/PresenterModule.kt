package com.kimboo.androidjobsnewsletter.di.module

import com.kimboo.mvvmkotlin.retrofit.repositories.RecipesRepository
import com.kimboo.mvvmkotlin.ui.main.MainPresenter
import com.kimboo.mvvmkotlin.ui.main.MainPresenterImpl
import dagger.Module
import dagger.Provides

@Module
class PresenterModule {

    @Provides
    fun provideMainPresenter(recipesRepository: RecipesRepository) : MainPresenter = MainPresenterImpl(recipesRepository)

}
