package com.hiteshchopra.data.remote.source

import com.hiteshchopra.data.remote.CookpadApiService
import com.hiteshchopra.data.remote.model.collection.CollectionsItemData
import com.hiteshchopra.data.remote.model.recipe.RecipeItemData
import com.hiteshchopra.data.safeApiCall
import com.hiteshchopra.domain.SafeResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class CookpadRemoteSource(
  private val cookpadApiService: CookpadApiService,
  private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ICookpadRemoteSource {
  override suspend fun getCollections(): SafeResult<ArrayList<CollectionsItemData>> {
    return safeApiCall(dispatcher) {
      cookpadApiService.getCollections()
    }
  }

  override suspend fun getCollectionById(id: Int): SafeResult<CollectionsItemData> {
    return safeApiCall(dispatcher) {
      cookpadApiService.getCollectionById(id)
    }
  }

  override suspend fun getCollectionRecipe(id: Int): SafeResult<RecipeItemData> {
    return safeApiCall(dispatcher) {
      cookpadApiService.getCollectionRecipe(id)
    }
  }

  override suspend fun getRecipes(): SafeResult<ArrayList<RecipeItemData>> {
    return safeApiCall(dispatcher) {
      cookpadApiService.getRecipes()
    }
  }

  override suspend fun getRecipeById(id: Int): SafeResult<RecipeItemData> {
    return safeApiCall(dispatcher) {
      cookpadApiService.getRecipeById(id)
    }
  }
}