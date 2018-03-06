package com.kimboo.mvvmkotlin.retrofit.repositories

import com.kimboo.mvvmkotlin.extensions.DataSource
import com.kimboo.mvvmkotlin.extensions.transformEntity
import com.kimboo.mvvmkotlin.model.Recipe
import com.kimboo.mvvmkotlin.retrofit.api.RecipesApi
import com.kimboo.mvvmkotlin.retrofit.mappers.RecipesMapper
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Agustin Tomas Larghi on 27/2/2018.
 * Email: agustin.tomas.larghi@gmail.com
 */
class RecipesRepositoryImp(val recipesApi: RecipesApi): RecipesRepository {

    override fun getRecipeList(): Observable<DataSource<List<Recipe>>> {
        return recipesApi.getRecipes()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .transformEntity(RecipesMapper()::fromServerToModel)
    }

}