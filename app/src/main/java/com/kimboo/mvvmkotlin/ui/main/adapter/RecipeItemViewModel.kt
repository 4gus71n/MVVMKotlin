package com.kimboo.mvvmkotlin.ui.main.adapter

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import com.kimboo.mvvmkotlin.model.Recipe

/**
 * Created by Agustin Tomas Larghi on 7/3/2018.
 * Email: agustin.tomas.larghi@gmail.com
 */
class RecipeItemViewModel(): ViewModel() {

    val recipe = ObservableField<Recipe>()

    constructor(recipeModel: Recipe) : this() {
        recipe.set(recipeModel)
    }

}