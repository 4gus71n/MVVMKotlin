package com.kimboo.mvvmkotlin.ui.main.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.kimboo.mvvmkotlin.databinding.ViewItemRecipeBinding
import com.kimboo.mvvmkotlin.model.Recipe

/**
 * Created by Agustin Tomas Larghi on 7/3/2018.
 * Email: agustin.tomas.larghi@gmail.com
 */
class RecipesAdapter(): RecyclerView.Adapter<RecipesAdapter.MainItemViewHolder>() {

    var recipes: List<Recipe> = ArrayList<Recipe>()
        set(values) {
            field = values
            notifyDataSetChanged()
        }

    override fun onBindViewHolder(holder: MainItemViewHolder?, position: Int) {
        holder!!.onBind(recipes[position])
    }

    override fun getItemCount(): Int = recipes.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MainItemViewHolder {
        val itemMainBinding = ViewItemRecipeBinding.inflate(LayoutInflater.from(parent!!.context))
        return MainItemViewHolder(itemMainBinding)
    }

    class MainItemViewHolder(var itemMainBinding: ViewItemRecipeBinding) : RecyclerView.ViewHolder(itemMainBinding.root) {
        fun onBind(recipe: Recipe) {
            itemMainBinding.recipeItemViewModel = RecipeItemViewModel(recipe)
            itemMainBinding.executePendingBindings()
        }

    }

}
