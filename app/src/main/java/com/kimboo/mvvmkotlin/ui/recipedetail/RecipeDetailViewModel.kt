package com.kimboo.mvvmkotlin.ui.recipedetail

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import com.kimboo.mvvmkotlin.model.Recipe
import com.kimboo.mvvmkotlin.retrofit.repositories.RandomUserRepository
import javax.inject.Inject

/**
 * Created by Agustin Tomas Larghi on 5/3/2018.
 * Email: agustin.tomas.larghi@gmail.com
 */
//If you need a context aware ViewModel you should use AndroidViewModel
class RecipeDetailViewModel @Inject constructor (val randomUserRepository: RandomUserRepository): ViewModel() {

    //Notice that the variables are read-only, but not their properties
    val recipe = ObservableField<Recipe>()

    fun getRecipe() {

    }


}