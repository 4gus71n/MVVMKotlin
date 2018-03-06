package com.kimboo.mvvmkotlin.ui.main

import com.kimboo.mvvmkotlin.extensions.DataSourceSubscriber
import com.kimboo.mvvmkotlin.extensions.subscribe
import com.kimboo.mvvmkotlin.model.Recipe
import com.kimboo.mvvmkotlin.retrofit.repositories.RecipesRepository

/**
 * Created by Agustin Tomas Larghi on 5/3/2018.
 * Email: agustin.tomas.larghi@gmail.com
 */
class MainPresenterImpl(val mainViewModel: MainViewModel, val recipesRepository: RecipesRepository): MainPresenter {

    override fun getRecipes() {
        recipesRepository.getRecipeList()
                .subscribe(object: DataSourceSubscriber<List<Recipe>>() {
                    override fun onResultNext(model: List<Recipe>) {
                        mainViewModel.recipes.set(model)
                    }
                })

    }

    override fun getChefInfo() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}