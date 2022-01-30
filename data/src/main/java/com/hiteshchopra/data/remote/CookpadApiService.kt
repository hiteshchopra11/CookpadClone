package com.hiteshchopra.data.remote

import com.hiteshchopra.data.remote.model.collection.CollectionsItemData
import com.hiteshchopra.data.remote.model.recipe.RecipeItemData
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path

interface CookpadApiService {
  companion object {
    private const val GET_COLLECTIONS = "collections"
    private const val GET_COLLECTION_BY_ID = "collections/{id}"
    private const val GET_COLLECTION_RECIPE = "collections/{id}/recipes"
    private const val GET_RECIPES = "recipes"
    private const val GET_RECIPE_BY_ID = "recipes/{id}"

    fun createRetrofitService(retrofit: Retrofit): CookpadApiService {
      return retrofit.create(CookpadApiService::class.java)
    }
  }

  @GET(GET_COLLECTIONS)
  suspend fun getCollections(): ArrayList<CollectionsItemData>

  @GET(GET_COLLECTION_BY_ID)
  suspend fun getCollectionById(
    @Path("id") id: Int
  ): CollectionsItemData

  @GET(GET_COLLECTION_RECIPE)
  suspend fun getCollectionRecipes(
    @Path("id") id: Int
  ): ArrayList<RecipeItemData>

  @GET(GET_RECIPES)
  suspend fun getRecipes(): ArrayList<RecipeItemData>

  @GET(GET_RECIPE_BY_ID)
  suspend fun getRecipeById(
    @Path("id") id: Int
  ): RecipeItemData
}