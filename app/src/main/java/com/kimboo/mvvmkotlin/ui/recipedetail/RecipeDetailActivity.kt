package com.kimboo.mvvmkotlin.ui.recipedetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kimboo.mvvmkotlin.R
import com.kimboo.mvvmkotlin.model.Recipe

/**
 * Created by Agustin Tomas Larghi on 31/3/2018.
 * Email: agustin.tomas.larghi@gmail.com
 */
class RecipeDetailActivity: AppCompatActivity() {

    //region Constant variables declartion
    companion object {
        val ARG_RECIPE_BUNDLE = "arg_recipe_bundle"

        fun getStartIntent(context: Context, recipe: Recipe): Intent {
            val intent = Intent(context, RecipeDetailActivity::class.java)
            intent.putExtra(ARG_RECIPE_BUNDLE, recipe)
            return intent
        }
    }
    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_no_toolbar)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, RecipeDetailFragment.newInstance(), RecipeDetailFragment.TAG)
                    .commit()
        }
    }

}