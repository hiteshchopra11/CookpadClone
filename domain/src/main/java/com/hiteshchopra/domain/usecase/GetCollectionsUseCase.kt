package com.hiteshchopra.domain.usecase

import com.hiteshchopra.domain.SafeResult
import com.hiteshchopra.domain.model.CollectionsItemDomain
import com.hiteshchopra.domain.repo.ICookpadRepo

class GetCollectionsUseCase(
  private val cookpadRepo: ICookpadRepo
) : BaseUseCase<SafeResult<List<CollectionsItemDomain>>, Unit> {
  override suspend fun perform(): SafeResult<List<CollectionsItemDomain>> {
    return cookpadRepo.getCollections()
  }
}