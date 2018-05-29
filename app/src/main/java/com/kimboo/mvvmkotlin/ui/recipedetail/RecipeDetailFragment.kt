package com.kimboo.mvvmkotlin.ui.recipedetail

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kimboo.mvvmkotlin.MyApp
import com.kimboo.mvvmkotlin.R
import com.kimboo.mvvmkotlin.databinding.FragmentRecipeDetailBinding
import com.kimboo.mvvmkotlin.di.modules.MyViewModelFactory
import javax.inject.Inject

/**
 * Created by Agustin Tomas Larghi on 31/3/2018.
 * Email: agustin.tomas.larghi@gmail.com
 */
class RecipeDetailFragment: Fragment() {

    //region Constant variables declaration
    companion object {
        var TAG: String = RecipeDetailFragment.javaClass.simpleName

        fun newInstance(): RecipeDetailFragment {
            val fragment = RecipeDetailFragment()
            fragment.arguments = Bundle()
            return fragment
        }
    }
    //endregion

    //region Variables declaration
    @Inject
    lateinit var viewModelFactory: MyViewModelFactory

    private lateinit var recipeDetailBinding: FragmentRecipeDetailBinding //Generated automatically
    private lateinit var recipeDetailViewModel: RecipeDetailViewModel
    //endregion

    //region Fragment's lifecycle methods
    fun onCreatesView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?) =
            inflater!!.inflate(R.layout.fragment_recipe_detail, container, false)

    fun onViewsCreated(view: View?, savedInstanceState: Bundle?) {

        MyApp.viewInjector.inject(this)

        recipeDetailBinding = FragmentRecipeDetailBinding.bind(view!!)
        recipeDetailViewModel = ViewModelProviders.of(this, viewModelFactory).get(RecipeDetailViewModel::class.java)
        recipeDetailBinding.recipeDetailViewModel = recipeDetailViewModel

        recipeDetailViewModel.getRecipe();

    }
    //endregion
}