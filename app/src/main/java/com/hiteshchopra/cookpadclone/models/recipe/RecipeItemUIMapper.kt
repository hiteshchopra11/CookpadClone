package com.hiteshchopra.cookpadclone.models.recipe

import com.hiteshchopra.domain.mapper.UiModelMapper
import com.hiteshchopra.domain.model.RecipeItemDomain

class RecipeItemUIMapper(
  private val recipeStepsUIMapper: RecipeStepsUIMapper,
  private val recipeUserUIMapper: RecipeUserUIMapper
) : UiModelMapper<RecipeItemDomain, RecipeItemUIModel> {

  override fun mapToPresentation(domain: RecipeItemDomain): RecipeItemUIModel {
    return RecipeItemUIModel(
      id = domain.id,
      imageUrl = domain.imageUrl,
      ingredients = domain.ingredients,
      publishedAt = domain.publishedAt,
      steps = domain.steps?.map { it?.let { it1 -> recipeStepsUIMapper.mapToPresentation(it1) } },
      story = domain.story,
      title = domain.title,
      user = domain.user?.let { recipeUserUIMapper.mapToPresentation(it) }
    )
  }

  override fun mapToDomain(ui: RecipeItemUIModel): RecipeItemDomain {
    return RecipeItemDomain(
      id = ui.id,
      imageUrl = ui.imageUrl,
      ingredients = ui.ingredients,
      publishedAt = ui.publishedAt,
      steps = ui.steps?.map { it?.let { it1 -> recipeStepsUIMapper.mapToDomain(it1) } },
      story = ui.story,
      title = ui.title,
      user = ui.user?.let { recipeUserUIMapper.mapToDomain(it) }
    )
  }
}