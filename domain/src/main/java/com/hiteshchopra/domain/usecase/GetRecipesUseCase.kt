package com.hiteshchopra.domain.usecase

import com.hiteshchopra.domain.SafeResult
import com.hiteshchopra.domain.model.RecipeItemDomain
import com.hiteshchopra.domain.repo.ICookpadRepo

class GetRecipesUseCase(
  private val cookpadRepo: ICookpadRepo
) : BaseUseCase<SafeResult<List<RecipeItemDomain>>, Unit> {
  override suspend fun perform(): SafeResult<List<RecipeItemDomain>> {
    return cookpadRepo.getRecipes()
  }
}