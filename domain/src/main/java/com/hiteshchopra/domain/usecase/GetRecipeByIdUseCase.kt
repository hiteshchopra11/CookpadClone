package com.hiteshchopra.domain.usecase

import com.hiteshchopra.domain.SafeResult
import com.hiteshchopra.domain.model.RecipeItemDomain
import com.hiteshchopra.domain.repo.ICookpadRepo

class GetRecipeByIdUseCase(
  private val cookpadRepo: ICookpadRepo
) : BaseUseCase<SafeResult<RecipeItemDomain>, Int> {
  override suspend fun perform(params: Int): SafeResult<RecipeItemDomain> {
    return cookpadRepo.getRecipeById(params)
  }
}