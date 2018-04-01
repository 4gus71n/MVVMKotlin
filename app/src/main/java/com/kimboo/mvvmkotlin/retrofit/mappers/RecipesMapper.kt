package com.kimboo.mvvmkotlin.retrofit.mappers

import com.kimboo.mvvmkotlin.model.Ingredient
import com.kimboo.mvvmkotlin.model.Recipe
import com.kimboo.mvvmkotlin.retrofit.responses.ApiRecipeResponse

/**
 * Created by Agustin Tomas Larghi on 6/3/2018.
 * Email: agustin.tomas.larghi@gmail.com
 */
class RecipesMapper {

    fun fromServerToModel(serverResponses: List<ApiRecipeResponse>): List<Recipe> {
        var result = ArrayList<Recipe>()

        for (response in serverResponses) {
            val recipe = fromServerToModel(response)
            result.add(recipe)
        }

        return result
    }

    fun fromServerToModel(response: ApiRecipeResponse): Recipe {
        var ingredients = ArrayList<Ingredient>()

        for (ingredientResponse in response.ingredients) {
            val ingredient = Ingredient(ingredientResponse.name, ingredientResponse.amount, ingredientResponse.units)
            ingredients.add(ingredient)
        }

        return Recipe(response.name, response.description, ingredients, response.chefName,
                response.chefId)
    }

}