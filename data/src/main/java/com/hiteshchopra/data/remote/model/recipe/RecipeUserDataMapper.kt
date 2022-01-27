package com.hiteshchopra.data.remote.model.recipe

import com.hiteshchopra.data.remote.mapper.EntityMapper
import com.hiteshchopra.domain.model.RecipeUserDomain

class RecipeUserDataMapper : EntityMapper<RecipeUserDomain, RecipeUserData> {
  override fun mapToDomain(data: RecipeUserData): RecipeUserDomain {
    return RecipeUserDomain(
      imageUrl = data.imageUrl,
      name = data.name
    )
  }

  override fun mapToData(domain: RecipeUserDomain): RecipeUserData {
    return RecipeUserData(
      imageUrl = domain.imageUrl,
      name = domain.name
    )
  }
}