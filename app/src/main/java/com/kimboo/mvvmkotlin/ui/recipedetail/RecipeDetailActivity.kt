package com.kimboo.mvvmkotlin.ui.recipedetail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kimboo.mvvmkotlin.R

/**
 * Created by Agustin Tomas Larghi on 31/3/2018.
 * Email: agustin.tomas.larghi@gmail.com
 */
class RecipeDetailActivity: AppCompatActivity() {

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