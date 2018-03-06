package com.kimboo.mvvmkotlin.di.components

import com.kimboo.androidjobsnewsletter.di.module.PresenterModule
import com.kimboo.mvvmkotlin.di.modules.ViewModelModule
import com.kimboo.mvvmkotlin.ui.main.MainFragment
import dagger.Component

@Component(dependencies = arrayOf(AppComponent::class),
        modules = arrayOf(PresenterModule::class, ViewModelModule::class))
interface ViewInjectorComponent {

    fun inject(fragment: MainFragment)

}