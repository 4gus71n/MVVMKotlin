package com.kimboo.mvvmkotlin.ui.main.adapter

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.view.View
import com.kimboo.mvvmkotlin.model.Recipe

/**
 * Created by Agustin Tomas Larghi on 7/3/2018.
 * Email: agustin.tomas.larghi@gmail.com
 */
class RecipeItemViewModel(var callback: Callback): ViewModel() {

    //region Variables declaration
    val recipe = ObservableField<Recipe>()
    //endregion

    //region ViewModel lifecycle methods declaration
    constructor(recipeModel: Recipe, callback: Callback) : this(callback) {
        recipe.set(recipeModel)
    }
    //endregion

    //region Public methods declaration
    fun onWholeLayoutClicked(view : View?) {
        callback.onWholeLayoutClicked(recipe.get()!!) //TODO Check this
    }
    //endregion

    //region Callback inferace declaration

    /**
     * To communicate back to the {@link RecipesAdapter}
     */
    interface Callback {
        fun onWholeLayoutClicked(recipe: Recipe)
    }
    //endregion

}