package com.hiteshchopra.data.remote.repo

import com.hiteshchopra.data.remote.model.collection.CollectionsItemDataMapper
import com.hiteshchopra.data.remote.model.recipe.RecipeItemDataMapper
import com.hiteshchopra.data.remote.source.ICookpadRemoteSource
import com.hiteshchopra.domain.SafeResult
import com.hiteshchopra.domain.SafeResult.Failure
import com.hiteshchopra.domain.SafeResult.NetworkError
import com.hiteshchopra.domain.SafeResult.Success
import com.hiteshchopra.domain.model.CollectionsItemDomain
import com.hiteshchopra.domain.model.RecipeItemDomain
import com.hiteshchopra.domain.repo.ICookpadRepo

class CookpadRepo(
  private val cookpadRemoteSource: ICookpadRemoteSource,
  private val collectionsItemDataMapper: CollectionsItemDataMapper,
  private val recipeItemDataMapper: RecipeItemDataMapper
) : ICookpadRepo {
  override suspend fun getCollections(): SafeResult<List<CollectionsItemDomain>> {
    return when (val response = cookpadRemoteSource.getCollections()) {
      is Success -> Success(response.data.map { collectionsItemDataMapper.mapToDomain(it) })
      is Failure -> Failure(response.exception)
      is NetworkError -> NetworkError
    }
  }

  override suspend fun getCollectionById(id: Int): SafeResult<CollectionsItemDomain> {
    return when (val response = cookpadRemoteSource.getCollectionById(id)) {
      is Success -> Success(collectionsItemDataMapper.mapToDomain(response.data))
      is Failure -> Failure(response.exception)
      is NetworkError -> NetworkError
    }
  }

  override suspend fun getCollectionRecipes(id: Int): SafeResult<List<RecipeItemDomain>> {
    return when (val response = cookpadRemoteSource.getCollectionRecipe(id)) {
      is Success -> Success(response.data.map { recipeItemDataMapper.mapToDomain(it) })
      is Failure -> Failure(response.exception)
      is NetworkError -> NetworkError
    }
  }

  override suspend fun getRecipes(): SafeResult<List<RecipeItemDomain>> {
    return when (val response = cookpadRemoteSource.getRecipes()) {
      is Success -> Success(response.data.map { recipeItemDataMapper.mapToDomain(it) })
      is Failure -> Failure(response.exception)
      is NetworkError -> NetworkError
    }
  }

  override suspend fun getRecipeById(id: Int): SafeResult<RecipeItemDomain> {
    return when (val response = cookpadRemoteSource.getRecipeById(id)) {
      is Success -> Success(recipeItemDataMapper.mapToDomain(response.data))
      is Failure -> Failure(response.exception)
      is NetworkError -> NetworkError
    }
  }
}