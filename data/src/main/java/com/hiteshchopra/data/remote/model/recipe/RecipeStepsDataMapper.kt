package com.hiteshchopra.data.remote.model.recipe

import com.hiteshchopra.data.remote.mapper.EntityMapper
import com.hiteshchopra.domain.model.RecipeStepsDomain

class RecipeStepsDataMapper : EntityMapper<RecipeStepsDomain, RecipeStepsData> {
  override fun mapToDomain(data: RecipeStepsData): RecipeStepsDomain {
    return RecipeStepsDomain(
      description = data.description,
      imageUrls = data.imageUrls
    )
  }

  override fun mapToData(domain: RecipeStepsDomain): RecipeStepsData {
    return RecipeStepsData(
      description = domain.description,
      imageUrls = domain.imageUrls
    )
  }
}