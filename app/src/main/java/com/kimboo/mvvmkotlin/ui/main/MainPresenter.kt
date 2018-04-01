package com.kimboo.mvvmkotlin.ui.main

import com.kimboo.mvvmkotlin.extensions.DataSource
import com.kimboo.mvvmkotlin.model.Recipe
import io.reactivex.Observable

/**
 * Created by Agustin Tomas Larghi on 5/3/2018.
 * Email: agustin.tomas.larghi@gmail.com
 */
interface MainPresenter {

    fun getRecipes() : Observable<DataSource<List<Recipe>>>

    fun getChefInfo()

}