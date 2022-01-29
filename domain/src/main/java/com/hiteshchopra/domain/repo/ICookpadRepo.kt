package com.hiteshchopra.domain.repo

import com.hiteshchopra.domain.SafeResult
import com.hiteshchopra.domain.model.CollectionsItemDomain
import com.hiteshchopra.domain.model.RecipeItemDomain

interface ICookpadRepo {
  suspend fun getCollections(): SafeResult<List<CollectionsItemDomain>>

  suspend fun getCollectionById(id: Int): SafeResult<CollectionsItemDomain>

  suspend fun getCollectionRecipes(id: Int): SafeResult<List<RecipeItemDomain>>

  suspend fun getRecipes(): SafeResult<List<RecipeItemDomain>>

  suspend fun getRecipeById(id: Int): SafeResult<RecipeItemDomain>
}