package com.kimboo.mvvmkotlin.ui.main

import android.arch.lifecycle.ViewModel
import android.databinding.BindingAdapter
import android.databinding.ObservableField
import android.support.v7.widget.RecyclerView
import com.kimboo.mvvmkotlin.extensions.DataSourceSubscriber
import com.kimboo.mvvmkotlin.extensions.subscribe
import com.kimboo.mvvmkotlin.model.Recipe
import com.kimboo.mvvmkotlin.retrofit.repositories.RecipesRepository
import com.kimboo.mvvmkotlin.ui.main.adapter.RecipesAdapter
import javax.inject.Inject

/**
 * Created by Agustin Tomas Larghi on 5/3/2018.
 * Email: agustin.tomas.larghi@gmail.com
 */
//If you need a context aware ViewModel you should use AndroidViewModel
class MainViewModel @Inject constructor (val recipesRepository: RecipesRepository): ViewModel() {

    //Notice that the variables are read-only, but not their properties
    val recipes = ObservableField<List<Recipe>>()
    val isLoading = ObservableField<Boolean>()

    //Note: The ViewModel method's signature needs to match the View callback method's signature.
    //For example, if we are hooking this method to a View's OnClickListener, we need to pass through a View parameter
    //In this case the SwipeRefresLayoutListener's onRefresh() method takes no arguments.
    fun getRecipes() {
        recipesRepository.getRecipeList()
                .subscribe(object: DataSourceSubscriber<List<Recipe>>() {
                    override fun onResultNext(model: List<Recipe>) {
                        recipes.set(model)
                        isLoading.set(false)
                    }
                })
    }

    companion object {
        @JvmStatic @BindingAdapter(value = "bind:data", requireAll = true)
        fun bindAdapter(recyclerView: RecyclerView, data: List<Recipe>?) {
            if (data != null) {
                var adapter = recyclerView.adapter as RecipesAdapter
                adapter.recipes = data
            }
        }
    }

}