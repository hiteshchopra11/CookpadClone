package com.hiteshchopra.domain.usecase

import com.hiteshchopra.domain.SafeResult
import com.hiteshchopra.domain.model.CollectionsItemDomain
import com.hiteshchopra.domain.repo.ICookpadRepo

class GetCollectionByIdUseCase(
  private val cookpadRepo: ICookpadRepo
) : BaseUseCase<SafeResult<CollectionsItemDomain>, Int> {
  override suspend fun perform(params: Int): SafeResult<CollectionsItemDomain> {
    return cookpadRepo.getCollectionById(params)
  }
}