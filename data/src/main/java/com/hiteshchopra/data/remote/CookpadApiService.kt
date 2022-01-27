package com.hiteshchopra.data.remote

import com.hiteshchopra.data.remote.model.recipe.RecipeItemData
import com.hiteshchopra.data.remote.model.collection.CollectionsItemData
import retrofit2.Retrofit
import retrofit2.http.GET

interface CookpadApiService {
  companion object {
    private const val GET_COLLECTIONS = "collections"
    private const val GET_COLLECTION_BY_ID = "collections/{id}"
    private const val GET_COLLECTION_RECIPE = "collections/{id}/recipe"
    private const val GET_RECIPES = "recipes"
    private const val GET_RECIPE_BY_ID = "recipes/{id}"

    fun createRetrofitService(retrofit: Retrofit): CookpadApiService {
      return retrofit.create(CookpadApiService::class.java)
    }
  }

  @GET(GET_COLLECTIONS)
  suspend fun getCollections(): ArrayList<CollectionsItemData>

  @GET(GET_COLLECTION_BY_ID)
  suspend fun getCollectionById(id: Int): CollectionsItemData

  @GET(GET_COLLECTION_RECIPE)
  suspend fun getCollectionRecipe(id: Int): RecipeItemData

  @GET(GET_RECIPES)
  suspend fun getRecipes(): ArrayList<RecipeItemData>

  @GET(GET_RECIPE_BY_ID)
  suspend fun getRecipeById(id: Int): RecipeItemData
}