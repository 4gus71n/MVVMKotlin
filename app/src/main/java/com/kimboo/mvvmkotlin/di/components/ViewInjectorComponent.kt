package com.kimboo.mvvmkotlin.di.components

import com.kimboo.mvvmkotlin.di.modules.ViewModelModule
import com.kimboo.mvvmkotlin.ui.main.MainFragment
import com.kimboo.mvvmkotlin.ui.recipedetail.RecipeDetailFragment
import dagger.Component

@Component(dependencies = arrayOf(AppComponent::class),
        modules = arrayOf(ViewModelModule::class))
interface ViewInjectorComponent {

    fun inject(fragment: MainFragment)

    fun inject(fragment: RecipeDetailFragment)

}