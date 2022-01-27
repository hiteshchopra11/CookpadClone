package com.hiteshchopra.data.remote.model.recipe

import com.hiteshchopra.data.remote.mapper.EntityMapper
import com.hiteshchopra.domain.model.RecipeItemDomain

class RecipeItemDataMapper(
  private val recipeStepsDataMapper: RecipeStepsDataMapper,
  private val recipeUserDataMapper: RecipeUserDataMapper
) : EntityMapper<RecipeItemDomain, RecipeItemData> {
  override fun mapToDomain(data: RecipeItemData): RecipeItemDomain {
    return RecipeItemDomain(
      id = data.id,
      imageUrl = data.imageUrl,
      ingredients = data.ingredients,
      publishedAt = data.publishedAt,
      steps = data.steps?.map { it?.let { it1 -> recipeStepsDataMapper.mapToDomain(it1) } },
      story = data.story,
      title = data.title,
      user = data.user?.let { recipeUserDataMapper.mapToDomain(it) }
    )
  }

  override fun mapToData(domain: RecipeItemDomain): RecipeItemData {
    return RecipeItemData(
      id = domain.id,
      imageUrl = domain.imageUrl,
      ingredients = domain.ingredients,
      publishedAt = domain.publishedAt,
      steps = domain.steps?.map { it?.let { it1 -> recipeStepsDataMapper.mapToData(it1) } },
      story = domain.story,
      title = domain.title,
      user = domain.user?.let { recipeUserDataMapper.mapToData(it) }
    )
  }
}