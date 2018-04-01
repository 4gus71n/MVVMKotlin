package com.kimboo.mvvmkotlin.ui.main

import com.kimboo.mvvmkotlin.extensions.DataSource
import com.kimboo.mvvmkotlin.model.Recipe
import com.kimboo.mvvmkotlin.retrofit.repositories.RecipesRepository
import io.reactivex.Observable

/**
 * Created by Agustin Tomas Larghi on 5/3/2018.
 * Email: agustin.tomas.larghi@gmail.com
 */
class MainPresenterImpl(val recipesRepository: RecipesRepository): MainPresenter {

    override fun getRecipes(): Observable<DataSource<List<Recipe>>> {
        return recipesRepository.getRecipeList()
    }

    override fun getChefInfo() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}