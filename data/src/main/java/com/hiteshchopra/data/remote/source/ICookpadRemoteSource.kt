package com.hiteshchopra.data.remote.source

import com.hiteshchopra.data.remote.model.collection.CollectionsItemData
import com.hiteshchopra.data.remote.model.recipe.RecipeItemData
import com.hiteshchopra.domain.SafeResult

interface ICookpadRemoteSource {
  suspend fun getCollections(): SafeResult<ArrayList<CollectionsItemData>>

  suspend fun getCollectionById(id: Int): SafeResult<CollectionsItemData>

  suspend fun getCollectionRecipe(id: Int): SafeResult<RecipeItemData>

  suspend fun getRecipes(): SafeResult<ArrayList<RecipeItemData>>

  suspend fun getRecipeById(id: Int): SafeResult<RecipeItemData>
}