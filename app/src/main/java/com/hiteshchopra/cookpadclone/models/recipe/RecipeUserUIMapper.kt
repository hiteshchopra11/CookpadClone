package com.hiteshchopra.cookpadclone.models.recipe

import com.hiteshchopra.domain.mapper.UiModelMapper
import com.hiteshchopra.domain.model.RecipeUserDomain

class RecipeUserUIMapper : UiModelMapper<RecipeUserDomain, RecipeUserUIModel> {
  override fun mapToPresentation(domain: RecipeUserDomain): RecipeUserUIModel {
    return RecipeUserUIModel(
      imageUrl = domain.imageUrl,
      name = domain.name
    )
  }

  override fun mapToDomain(ui: RecipeUserUIModel): RecipeUserDomain {
    return RecipeUserDomain(
      imageUrl = ui.imageUrl,
      name = ui.name
    )
  }
}