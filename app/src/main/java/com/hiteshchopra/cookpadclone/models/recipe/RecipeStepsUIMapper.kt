package com.hiteshchopra.cookpadclone.models.recipe

import com.hiteshchopra.domain.mapper.UiModelMapper
import com.hiteshchopra.domain.model.RecipeStepsDomain

class RecipeStepsUIMapper : UiModelMapper<RecipeStepsDomain, RecipeStepsUIModel> {
  override fun mapToDomain(ui: RecipeStepsUIModel): RecipeStepsDomain {
    return RecipeStepsDomain(
      description = ui.description,
      imageUrls = ui.imageUrls
    )
  }

  override fun mapToPresentation(domain: RecipeStepsDomain): RecipeStepsUIModel {
    return RecipeStepsUIModel(
      description = domain.description,
      imageUrls = domain.imageUrls
    )
  }
}