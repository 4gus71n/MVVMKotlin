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
class RecipesAdapter(var callback: RecipesAdapter.Callback): RecyclerView.Adapter<RecipesAdapter.MainItemViewHolder>(),
        RecipeItemViewModel.Callback {

    //region Variables declaration
    var recipes: List<Recipe> = ArrayList<Recipe>()
        set(values) {
            field = values
            notifyDataSetChanged()
        }
    //endregion

    //region Adapter's lifecycle methods declaration
    override fun onBindViewHolder(holder: MainItemViewHolder?, position: Int) {
        holder!!.onBind(recipes[position])
    }

    override fun getItemCount(): Int = recipes.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MainItemViewHolder {
        val itemMainBinding = ViewItemRecipeBinding.inflate(LayoutInflater.from(parent!!.context))
        return MainItemViewHolder(itemMainBinding, this)
    }
    //endregion

    //region Adapter callback interface declaration

    /**
     * To communicate back to the Fragment/Activity
     */
    interface Callback {
        fun onWholeLayoutClicked(recipe: Recipe);
    }
    //endregion

    //region RecipeItemViewModel.Callback implementation
    override fun onWholeLayoutClicked(recipe: Recipe) {
        callback.onWholeLayoutClicked(recipe)
    }
    //endregion

    //region ViewHolder class declaration
    class MainItemViewHolder(var itemMainBinding: ViewItemRecipeBinding, var callback: RecipeItemViewModel.Callback) :
            RecyclerView.ViewHolder(itemMainBinding.root) {
        fun onBind(recipe: Recipe) {
            itemMainBinding.recipeItemViewModel = RecipeItemViewModel(recipe, callback)
            itemMainBinding.executePendingBindings()
        }
    }
    //endregion

}
